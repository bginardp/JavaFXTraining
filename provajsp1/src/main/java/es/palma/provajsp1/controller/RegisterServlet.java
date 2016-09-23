package es.palma.provajsp1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.palma.provajsp1.dao.UserDAO;
import es.palma.provajsp1.dao.UserDAOImpl;
import es.palma.provajsp1.entities.UserBean;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.out.println("################## RegisterServlet processRequest ######################");
       
        /*UserBean usuari =new UserBean();
        usuari.setUserName(request.getParameter("txtUserName"));
        usuari.setPassword(request.getParameter("txtPass"));
        */
              
            RequestDispatcher rd=request.getRequestDispatcher("/pages/register.jsp");
            // request.setAttribute("uname", usuari.getFirstName());
            rd.forward(request, response);
       

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


}
