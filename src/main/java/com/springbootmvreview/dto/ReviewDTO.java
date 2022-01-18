package com.springbootmvreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long rvnum; // review 번호
    private Long mno; // movie 번호
    private Long mid; // member id

    private String nickname; // member nickname
    private String email; // member email
    private int grade;
    private String text;
    private LocalDateTime regDate, modDate;
}
