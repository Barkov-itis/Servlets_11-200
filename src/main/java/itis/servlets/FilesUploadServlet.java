package itis.servlets;

import itis.models.FileInfo;
import itis.repository.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uploaded/files")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {

    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException{
        this.fileService = (FileService) config.getServletContext().getAttribute("filesUploadService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String currentLine = reader.readLine();
//        while (currentLine != null) {
//            System.out.println(currentLine);
//            currentLine = reader.readLine();
//        }
//        Part part = request.getPart("file");
//        System.out.println(part.getSubmittedFileName() + " ");
//        System.out.println(part.getContentType() + " ");
//        System.out.println(part.getSize());
//
//        Files.copy(part.getInputStream(), Paths.get("C://Users/tron5/IdeaProjects/Servlets_11_206/src/main/resources/files/" + part.getSubmittedFileName()));
//        response.sendRedirect("/files");
        String fileId = request.getParameter("id");
        //получаем информацию о загруженном файле
        FileInfo fileInfo = fileService.getFileInfo(Long.parseLong(fileId));
        // в ответе тип данных
        response.setContentType(fileInfo.getType());
        // указали в ответ размер данных
        response.setContentLength(fileInfo.getSize().intValue());
        // указали в ответ оригинальное название файла
        response.setHeader("Content-Discription", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
        // записываем в ответ данные файла
        fileService.writeFileFromStorage(Long.parseLong(fileId), response.getOutputStream());
        response.flushBuffer();
    }
}
