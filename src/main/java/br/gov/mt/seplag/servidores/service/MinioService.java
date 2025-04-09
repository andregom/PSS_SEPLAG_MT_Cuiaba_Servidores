package br.gov.mt.seplag.servidores.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    // Upload de arquivo com nome único
    public String uploadFile(MultipartFile file) throws Exception {
        String originalName = file.getOriginalFilename();
        String objectName = UUID.randomUUID() + "_" + originalName;

        try (InputStream inputStream = file.getInputStream()) {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();

            minioClient.putObject(args);
        }

        return objectName;
    }

    // Geração de URL com expiração
    public String generatePresignedUrl(String objectName) throws Exception {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .method(Method.GET)
                .expiry(5, TimeUnit.MINUTES)
                .build();

        return minioClient.getPresignedObjectUrl(args);
    }
}
