package org.hotel.back.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class S3ConfigTest {

    @Autowired
    private S3Config s3Config;


    @Test
    void tesst(){
        String filePath = "C:/PROJECT/TAELT/STUDY-GROUP-ACTIVITY/multi-module/back/temp/404.png";

        File file = new File(filePath);

        String uploadName = s3Config.upload(filePath);

        System.out.println(uploadName);
    }



}