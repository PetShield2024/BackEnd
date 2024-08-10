package com.example.petshield.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.petshield.config.AmazonConfig;
import com.example.petshield.domain.Uuid;
import com.example.petshield.repository.UuidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String generateDogKeyName(Uuid uuid) {
        return amazonConfig.getDogPath() + '/' + uuid.getUuid();
    }

    public String generateObesityKeyName(Uuid uuid) {
        return amazonConfig.getObesityPath() + '/' + uuid.getUuid();
    }


    public String uploadFile(String keyName, MultipartFile file) {
        System.out.println(keyName);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        // 파일의 MIME 타입을 설정합니다.
        String contentType = file.getContentType();
        if (contentType != null) {
            metadata.setContentType(contentType);
        } else {
            // MIME 타입을 알 수 없는 경우 기본값을 설정합니다.
            metadata.setContentType("application/octet-stream");
        }

        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e) {
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

}
