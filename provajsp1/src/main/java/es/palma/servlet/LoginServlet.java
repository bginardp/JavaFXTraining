package es.palma.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProvaServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public LoginServlet() {
       System.out.println("################################## Servlet inicialitzat ###################################");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.out.println("################## process Request ######################");


        String name=request.getParameter("txtUserName");
        String pass=request.getParameter("txtPass");

        if(name.equalsIgnoreCase("bernat")&& pass.equalsIgnoreCase("1234"))
        {
        	System.out.println("################################## Success ###################################");
              RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/pages/success.jsp");
            request.setAttribute("uname", name);
            rd.forward(request, response);
        }
        else
        	/* if name&pass not match then it display error page */
        {
        	System.out.println("################################## Error ###################################");
        	request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
