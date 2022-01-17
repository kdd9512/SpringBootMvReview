package com.springbootmvreview.dto;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long mno;

    private String title;

    @Builder.Default
    private List<MovieImgDTO> imageDTOList = new ArrayList<>();

    // 영화 평균 평점
    private double avg;



}