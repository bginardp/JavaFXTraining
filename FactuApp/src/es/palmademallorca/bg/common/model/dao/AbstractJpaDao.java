package es.palmademallorca.bg.common.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dominik Dorn
 */
public class AbstractJpaDao<TYPE> {
    @PersistenceContext
    protected EntityManager em;

    protected Class entityClass;

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public AbstractJpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];
    }

    public AbstractJpaDao(Class clazz) {
        this.entityClass = clazz;
    }

    public EntityManager getEm() {
        return em;
    }

    public AbstractJpaDao setEm(EntityManager em) {
        this.em = em;
        return this;
    }



    public TYPE persist(TYPE item) {
        if (item == null)
            throw new PersistenceException("Item may not be null");
        em.persist(item);
        return item;
    }


    public TYPE update(TYPE item) {
        if (item == null)
            throw new PersistenceException("Item may not be null");

        em.merge(item);
        return item;
    }


    public List<TYPE> getAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }


    public TYPE getById(Long id) {
        if (id == null || id < 1)
            throw new PersistenceException("Id may not be null or negative");

        return (TYPE) em.find(entityClass, id);
    }


    public void delete(TYPE item) {
        if (item == null)
            throw new PersistenceException("Item may not be null");

        em.remove(em.merge(item));
    }


    public List<TYPE> findByAttributes(Map<String, String> attributes) {
        List<TYPE> results;
        //set up the Criteria query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TYPE> cq = cb.createQuery(getEntityClass());
        Root<TYPE> foo = cq.from(getEntityClass());

        List<Predicate> predicates = new ArrayList<Predicate>();
        for(String s : attributes.keySet())
        {
            if(foo.get(s) != null){
                predicates.add(cb.like((Expression) foo.get(s), "%" + attributes.get(s) + "%" ));
            }
        }
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<TYPE> q = em.createQuery(cq);

        results = q.getResultList();
        return results;
    }
}
