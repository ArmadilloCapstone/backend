package com.example.dolbomi.repository;

import com.example.dolbomi.domain.UploadedFile;

public interface FileRepository {
    UploadedFile saveFileInfo(Long newsId, String originfilename);
}
