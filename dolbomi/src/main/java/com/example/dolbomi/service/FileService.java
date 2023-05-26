package com.example.dolbomi.service;

import com.example.dolbomi.domain.UploadedFile;
import com.example.dolbomi.repository.FileRepository;

public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    public UploadedFile saveFileInfo(Long newsId, String originFilename){
        return fileRepository.saveFileInfo(newsId, originFilename);
    }
}
