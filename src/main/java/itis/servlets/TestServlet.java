package itis.servlets;

import itis.dto.SignUpForm;
import itis.repository.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signUp")
public class TestServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/profile.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm form = new SignUpForm();
        form.setFirstName(req.getParameter("name"));
        form.setPassword(req.getParameter("password"));

        try {
            signUpService.signUp(form);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}