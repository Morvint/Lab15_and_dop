package suka.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainPage", value = "/MainPage")
public class Page extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><head><link rel=\"StyleSheet\"  type=\"text/css\"></head><body>");
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            request.getRequestDispatcher("link.html").include(request,response);
            String name = (String)session.getAttribute("name");
            out.print("welcome " + name);
            printAll(out, name, 1);
        }
        else
        {
            request.getRequestDispatcher("linkin.html").include(request,response);
            out.print("welcome");
            printAll(out, "0", 0);
        }
        out.println("</body></html>");
        out.close();


    }
    private void printAll(PrintWriter out, String name, int flag)throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\tv_20\\IdeaProjects\\Lab15\\src\\main\\java\\suka\\lab15\\ads.txt"));

        String s;
        String [] str;
        AdList ads = new AdList();
        while((s = in.readLine()) != null){
            str = s.split(";");
            ads.add(str[0], str[1], str[2], str[3]);
        }

        for(int i = 0; i < ads.size(); i++){
            out.println("<table border = \"2\" width = \"100%\">" +
                    "<caption><h2>" + ads.getAd(i).getHeader() + "</h2></caption>" +
                    "<tr>" +
                    "<td rowspan=\"2\">" + "<div id = \"text\">" + ads.getAd(i).getDescription() + "</div></td>" +
                    "<td width = \"25%\">author: " + ads.getAd(i).getName() + "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>date: " + ads.getAd(i).getTime() +"</td>" +
                    "</tr>" +
                    "</table>");
            if (name.equals(ads.getAd(i).getName()) && (flag == 1)){
                out.println("<form action=\"remove_message\" method=\"post\" class=\"inline\">" +
                        "<input type=\"hidden\" name=\"header\" value=\"" +
                        ads.getAd(i).getHeader() + "\">" +
                        "<button type=\"submit\" name=\"submit_param\" value=\"delete\" class=\"link-button\">" +
                        "Delete" +
                        "</button>" +
                        "</form>");
            }
        }
        in.close();
        out.close();
    }
}