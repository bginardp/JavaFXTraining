package es.palma.provajsp1.dao;

import java.util.List;

import es.palma.provajsp1.entities.UserBean;

public interface UserDAO {

	public UserBean login(UserBean bean);

	public List<UserBean> getUserList();
}
