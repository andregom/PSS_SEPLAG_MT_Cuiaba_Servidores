package br.gov.mt.seplag.servidores.controller;

import br.gov.mt.seplag.servidores.service.MinioService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/fotos")
public class MinioController {

    private final MinioService minioService;

    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            String objectName = minioService.uploadFile(file);
            return ResponseEntity.ok("Upload bem-sucedido: " + objectName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/url")
    public ResponseEntity<String> getUrl(@RequestParam("objectName") String objectName) {
        try {
            String url = minioService.generatePresignedUrl(objectName);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }
}
