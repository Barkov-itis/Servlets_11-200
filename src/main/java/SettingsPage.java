import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setting")
public class SettingsPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] color = request.getCookies();
        for (int i = 0; i < color.length; i++) {
            System.out.println(color[i].getValue() + " " + color[i].getName());
        }
        request.getRequestDispatcher("/jsp/settings.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String color = request.getParameter("color");
        Cookie colorCookie = new Cookie("color", color);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                 colorCookie.setMaxAge(60);
        response.addCookie(colorCookie);
        response.sendRedirect("/setting");
    }
}
