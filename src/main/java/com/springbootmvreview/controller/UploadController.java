package com.springbootmvreview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    @Value("${com.springbootmvreview.upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles){

        for (MultipartFile uploadFile: uploadFiles) {

            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("이미지 타입 파일이 아닙니다.");
                return;
            }

            // 실제 파일 이름. IE, EDGE 는 전체경로가 들어오므로 이하와 같이 처리.
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName : " + fileName);

            // 날짜 폴더 생성.
            String folderPath = makeFolder();

            // UUID 로 파일명 겹침 방지.
            String uuid = UUID.randomUUID().toString();

            // 저장 파일 이름 중간에 "_" 를 이용하여 구분.
            String saveName = uploadPath + File.separator + folderPath +
                    File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("//", File.separator);

        // make folder
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;

    }
}
