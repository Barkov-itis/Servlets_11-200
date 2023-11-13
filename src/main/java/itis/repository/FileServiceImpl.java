package itis.repository;

import itis.models.FileInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements FileService{

    private FilesRepository filesRepository;

    public FileServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .size(size)
                .type(contentType)
                .build();
        try {
            Files.copy(file, Paths.get("C://Users/tron5/IdeaProjects/Servlets_11-200/src/main/webapp/files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            filesRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
        // нашли файл в базе
        FileInfo fileInfo = filesRepository.findById(fileId);
        // нашли файл на диске
        File file = new File("C://Users/tron5/IdeaProjects/Servlets_11-200/src/main/webapp/files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            // записали его в ответ
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return filesRepository.findById(fileId);
    }
}
