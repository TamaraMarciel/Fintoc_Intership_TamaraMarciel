package com.fintoc.fintoc.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/images")
public class ImageController {
    
    private final ResourceLoader resourceLoader;
    
    public ImageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            // Cargar imagen desde classpath (src/main/resources/images/)
            Resource resource = resourceLoader.getResource("classpath:images/" + filename);
            
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
            
            // Detectar tipo de contenido
            String contentType = Files.probeContentType(resource.getFile().toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

