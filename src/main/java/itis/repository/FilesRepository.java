package itis.repository;

import itis.models.FileInfo;

import java.util.List;

public interface FilesRepository {
    void save(FileInfo entity);
    FileInfo findById(Long id);
//    List<FileInfo> findAll();
}
