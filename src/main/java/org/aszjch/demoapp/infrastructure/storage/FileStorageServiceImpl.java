package org.aszjch.demoapp.infrastructure.storage;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aszjch.demoapp.infrastructure.config.MinIOConnetionDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
@RequiredArgsConstructor
class FileStorageServiceImpl implements ExternalStorageService {

    private final MinioClient s3Client;
    private final MinIOConnetionDetails minIOConnectionDetails;

    @Override
    public ObjectWriteResponse upload(MultipartFile file) {
        log.info("Uploading import file with name {} to S3.", file.getOriginalFilename());

        ObjectWriteResponse response = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.getBytes());
            PutObjectArgs putArgs = PutObjectArgs.builder()
                    .bucket(minIOConnectionDetails.bucket())
                    .object(file.getName())
                    .stream(byteArrayInputStream, byteArrayInputStream.available(), -1)
                    .contentType(file.getContentType())
                    .build();

            response = s3Client.putObject(putArgs);
            log.info("File {} has been uploaded", file.getName());
            return response;
        } catch (Exception e) {
            log.error("Exception occurred during uploading file {}", file.getName(), e);
        }
        return response;
    }

    @PostConstruct
    public void init() throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
                              NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException,
                              XmlParserException, InternalException {
        if (!s3Client.bucketExists(BucketExistsArgs.builder()
                                           .bucket(minIOConnectionDetails.bucket())
                                           .build())) {
            s3Client.makeBucket(MakeBucketArgs.builder()
                                        .bucket(minIOConnectionDetails.bucket())
                                        .build());
        }

    }

}
