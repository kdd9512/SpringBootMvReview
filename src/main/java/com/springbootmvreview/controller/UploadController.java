package com.springbootmvreview.controller;

import com.springbootmvreview.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    @Value("${com.springbootmvreview.upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {

            // 이미지 이외에는 업로드 할 수 없게 처리.
            // 지정 타입의 파일이 아니면 log 에서 경고 출력 후 return.
            if (!uploadFile.getContentType().startsWith("image")) {
                log.warn("이미지 타입 파일이 아닙니다.");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // 실제 파일 이름. IE, EDGE 는 전체경로가 들어오므로 이하와 같이 처리.
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName : " + fileName);
            log.info("originalName : " + originalName);

            // 날짜 폴더 생성.
            String folderPath = makeFolder();

            // UUID 로 파일명 겹침 방지.
            String uuid = UUID.randomUUID().toString();

            // 저장 파일 이름과 부여될 UUID 중간에 "_" 를 이용하여 구분.
            String saveName = uploadPath + File.separator + folderPath +
                    File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);

                // 섬네일 파일명을 생성.
                String thumbnailSaveName = uploadPath + File.separator + folderPath +
                        File.separator + "/s_" + uuid + "_" + fileName; // 이름 중간에 /s_ 가 들어가게 설정.

                // 섬네일 생성. Thumbnailator 이용하여 thumbnail 이미지 파일 생성. 크기는 100 * 100
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

                resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("//", File.separator);

        // make folder.
        // uploadPath( = c:\JAVA\galupload) + folderPath( = 20??/??/??)
        File uploadPathFolder = new File(uploadPath, folderPath);
        System.out.println("uploadPathFolder : " + uploadPathFolder);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }


    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName, String size){

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("fileName : " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);

            if (size != null && size.equals("1")) {
                file = new File(file.getParent(), file.getName().substring(2));
            }

            log.info("file : " + file);

            HttpHeaders header = new HttpHeaders();

            // MIME 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){

        String srcFileName = null;

        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());
            result = thumbnail.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
