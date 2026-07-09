package com.anapoornabackend.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin
public class UploadController {
	private static final String UPLOAD_DIR =
	        "C:/Users/ELCOT/Desktop/internproject/anapoorna/annapoorna_uploads/";

    @PostMapping
    public ResponseEntity<String> uploadImage(
            @RequestParam("image") MultipartFile file)
            throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected");
        }

        File directory = new File(UPLOAD_DIR);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path path = Paths.get(UPLOAD_DIR + fileName);

        Files.write(path, file.getBytes());

        return ResponseEntity.ok("/uploads/" + fileName);
    }
}