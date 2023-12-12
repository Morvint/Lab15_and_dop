package suka.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Logout extends HttpServlet{
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getSession().invalidate();
        out.println("<html><body>");
        request.getRequestDispatcher("linkin.html").include(request,response);
        out.println("Successful Exit");
        out.println("</body></html>");
        out.close();

    }

}