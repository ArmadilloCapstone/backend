package com.example.dolbomi.repository;

import com.example.dolbomi.domain.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileRepository {
    UploadedFile saveFileInfo(Long newsId, String originfilename);
    List<UploadedFile> getFilesByNo(Long no);
    void removeFileById(String domain, Long newsid);
    void deleteFileInfo(String domain, Long newsid);
}
