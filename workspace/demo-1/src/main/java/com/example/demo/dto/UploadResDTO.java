package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResDTO {

    private String fileName;
    private String uuid;
    private String folderPath;

//    public String getImageURL() {
//        return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, StandardCharsets.UTF_8);
//    }
}
