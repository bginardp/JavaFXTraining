package es.palma.provajsp1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.palma.provajsp1.dao.UserDAO;
import es.palma.provajsp1.dao.UserDAOImpl;
import es.palma.provajsp1.entities.UserBean;

/**
 * Servlet implementation class ProvaServlet
 */
public class ListUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ListUsersServlet() {
       System.out.println("################################## ListUsersServlet inicialitzat ###################################");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.out.println("################## ListUsersServlet processRequest ######################");
        List<UserBean> llista=null;
        UserDAO userDAO=new UserDAOImpl();
        llista=userDAO.getUserList();
        if (llista!=null) {
            RequestDispatcher rd=request.getRequestDispatcher("/pages/listusers.jsp");
            request.setAttribute("users", llista);
            rd.forward(request, response);
        }
        else
        	/* if name&pass not match then it display error page */
        {
        	System.out.println("################################## ListUsersServlet Error ###################################");
        	request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        }

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
