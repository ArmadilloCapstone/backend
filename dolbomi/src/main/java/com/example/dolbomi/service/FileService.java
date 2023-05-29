package com.example.dolbomi.service;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.UploadedFile;
import com.example.dolbomi.repository.FileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public UploadedFile saveFileInfo(Long newsId, String originFilename){
        return fileRepository.saveFileInfo(newsId, originFilename);
    }

    public List<UploadedFile> getFilesByNo(Long no){
        List<UploadedFile> files= fileRepository.getFilesByNo(no);
        return files;
    }

    public void removeFileById(String domain, Long newsid){
        fileRepository.removeFileById(domain, newsid);
    }

    public void deleteFileInfo(String domain, Long newsid){
        fileRepository.deleteFileInfo(domain, newsid);
    }
}