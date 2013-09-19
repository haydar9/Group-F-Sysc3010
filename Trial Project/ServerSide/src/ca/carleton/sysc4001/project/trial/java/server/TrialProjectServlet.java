package ca.carleton.sysc4001.project.trial.java.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TrialProjectServlet
 * 
 * FIXME Welcome-file overrides servlet!? 
 */
//@WebServlet("/fservlet")
public class TrialProjectServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3352070522499807626L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrialProjectServlet() {
        super();
        //FIXME change to an appropriate logger
        System.out.println("TrialProjectServlet - constructor");
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//simple demo
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet Get</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
