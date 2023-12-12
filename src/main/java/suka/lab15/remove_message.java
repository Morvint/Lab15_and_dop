package suka.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class remove_message extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><head><link rel=\"StyleSheet\"  type=\"text/css\"></head><body>");
        request.getRequestDispatcher("link.html").include(request,response);
        String name = (String) request.getSession(false).getAttribute("name");
        String head = request.getParameter("header");
        out.println("<p>" + name+"</p>");
        out.println("<p>" + head+"</p>");
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\tv_20\\IdeaProjects\\Lab15\\src\\main\\java\\suka\\lab15\\ads.txt"));

            String s;
            String [] str;
            AdList ads = new AdList();
            while((s = in.readLine()) != null){
                str = s.split(";");
                ads.add(str[0], str[1], str[2], str[3]);
            }

            PrintWriter writer = new PrintWriter("C:\\Users\\tv_20\\IdeaProjects\\Lab15\\src\\main\\java\\suka\\lab15\\ads.txt");
            writer.print("");
            writer.close();

            for(int i = 0; i < ads.size(); i++){
                if(head.equals(ads.getAd(i).getHeader()) && name.equals(ads.getAd(i).getName())){
                    out.println("delete");
                    continue;
                }

                StringBuilder st = new StringBuilder();
                if (i == ads.size()){
                    st.append(ads.getAd(i).getHeader() + ";");
                    st.append(ads.getAd(i).getDescription() + ";");
                    st.append(ads.getAd(i).getName() + ";");
                    st.append(ads.getAd(i).getTime());
                    String text = st.toString();
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter("C:\\Users\\tv_20\\IdeaProjects\\Lab15\\src\\main\\java\\suka\\lab15\\ads.txt",true));
                    writer1.write(text);
                    writer1.close();
                }else{
                    st.append(ads.getAd(i).getHeader() + ";");
                    st.append(ads.getAd(i).getDescription() + ";");
                    st.append(ads.getAd(i).getName() + ";");
                    st.append(ads.getAd(i).getTime() + "\n");
                    String text = st.toString();
                    BufferedWriter writer2 = new BufferedWriter(new FileWriter("C:\\Users\\tv_20\\IdeaProjects\\Lab15\\src\\main\\java\\suka\\lab15\\ads.txt",true));
                    writer2.write(text);
                    writer2.close();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.println();
        out.println("</body></html>");
        out.close();
    }

}