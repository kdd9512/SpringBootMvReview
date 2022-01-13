package com.springbootmvreview.dto;

import lombok.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieImgDTO {

    private String uuid;
    private String imgName;
    private String path;

    public String getImageURL() {
        try {
            return URLEncoder.encode(path + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(path + "/s_" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
