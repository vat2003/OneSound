package com.project.shopapp.Service.imp;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.shopapp.Service.UploadService;

import jakarta.servlet.ServletContext;

/**
 * UploadServiceImpl
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext context;

    @Override
    public File save(MultipartFile file, String folder) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty, please choose a file.");
        }
        try {
            File dir = new File(context.getRealPath("upload/" + folder).toString());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filename = "upload-" + System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File savedFile = new File(dir, filename);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } // TODO Auto-generated method stub
    }

}