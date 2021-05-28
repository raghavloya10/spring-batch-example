package io.rl.spring_batch.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.rl.spring_batch.exception.StorageException;

@Service
public class StorageService {
	
	@Value("${root}")
	private String path;
	
	public void uploadFile(MultipartFile file) throws StorageException {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        try {
//          var fileName = file.getOriginalFilename();
            var is = file.getInputStream();
            String basePathOfClass = getClass().getProtectionDomain()
            		.getCodeSource().getLocation().getFile();
            System.out.println(">>>base path: "+basePathOfClass);
            Files.copy(is, Paths.get(this.path+"data.csv"),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully");
        } catch (IOException e) {
            var msg = String.format("Failed to store file %f", file.getName());
            throw new StorageException(msg, e);
        }
    }
}
