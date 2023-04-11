package org.hotel.back.util;

import org.hotel.back.config.exception.FileUploadException;
import org.hotel.back.data.dto.FileDTO;
import org.hotel.back.data.dto.UploadDTO;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocalUploader {

    @Value("${upload.path}")
    private String path;

    public List<String> uploadLocal(UploadDTO fileDTO){
        if(!fileDTO.getFiles().isEmpty()){
            final List<String> list = new ArrayList<>();

            fileDTO.getFiles().forEach(multipartFile -> {

                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(path, uuid+"_"+multipartFile.getOriginalFilename());

                try{
                    multipartFile.transferTo(savePath);

                    list.add(savePath.toFile().getAbsolutePath());

                } catch (IOException e) {
                    throw new FileUploadException("Exception");
                }

            });

            return list;
        }
        return null;
    }
}
