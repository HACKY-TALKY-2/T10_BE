package com.channeltalk.teamten.s3.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface S3Service {

    // S3 이미지 저장
    String saveUploadFile(MultipartFile multipartFileList) throws IOException;


}
