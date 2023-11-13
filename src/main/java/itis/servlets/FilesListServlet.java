package itis.servlets;

import itis.repository.FilesRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/files")
public class FilesListServlet extends HttpServlet {

    private FilesRepository filesRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        filesRepository = (FilesRepository) config.getServletContext().getAttribute("filesRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("FilesForJsp", filesRepository.findAll());
        req.getRequestDispatcher("/jsp/files.jsp").forward(req, resp);
    }
}
