package org.hotel.back.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.dto.FileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Config {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;
    @Value("${upload.path}")
    private String path;


    //업로드
    public String upload(String filePath) throws RuntimeException{
        filePath = path+File.separator+filePath;
        log.info("S3 파일 경로 {}",filePath);

        File targetFile = new File(filePath);

        String uploadImageUrl = putS3(targetFile,targetFile.getName());

        removeOriginalFile(targetFile);

        return uploadImageUrl;
    }

    //S3 업로드
    private String putS3(File uploadFile,String fileName) throws RuntimeException{
        amazonS3Client.putObject(new PutObjectRequest(bucket,fileName,uploadFile).withCannedAcl(CannedAccessControlList.
                PublicRead));
        return amazonS3Client.getUrl(bucket,fileName).toString();
    }

    private void removeOriginalFile(File targetFile){
        if(targetFile.exists() && targetFile.delete()){
            log.info("FILE DELETED 성공!+@#!#");
            return;
        }
        log.info("FILE 삭제 실패ㅣ!_#@");
    }

    public void removeS3File(String fileName){
        final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, fileName);

        amazonS3Client.deleteObject(deleteObjectRequest);
    }


}
