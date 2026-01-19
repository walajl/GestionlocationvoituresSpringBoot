package com.GestionLocationVoiture.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {
    
    private final String uploadDir = "uploads/";
    
    public FileController() {
        // Create uploads directory if it doesn't exist
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Fichier vide");
            return ResponseEntity.badRequest().body(error);
        }
        
        try {
            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null && originalFilename.contains(".") 
                ? originalFilename.substring(originalFilename.lastIndexOf(".")) 
                : ".jpg";
            String newFilename = UUID.randomUUID().toString() + extension;
            
            // Save file
            Path path = Paths.get(uploadDir + newFilename);
            Files.write(path, file.getBytes());
            
            // Return URL
            Map<String, String> response = new HashMap<>();
            response.put("url", "/uploads/" + newFilename);
            response.put("filename", newFilename);
            
            return ResponseEntity.ok(response);
            
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erreur lors de l'upload: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }
    
    @DeleteMapping("/{filename}")
    public ResponseEntity<?> deleteFile(@PathVariable String filename) {
        try {
            Path path = Paths.get(uploadDir + filename);
            Files.deleteIfExists(path);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
