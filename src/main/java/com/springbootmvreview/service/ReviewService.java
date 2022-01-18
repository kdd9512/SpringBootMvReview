package com.springbootmvreview.service;

import com.springbootmvreview.dto.ReviewDTO;
import com.springbootmvreview.entity.MemberEntity;
import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfMovie(Long mno); // 모든 영화리뷰 가져오기

    Long register(ReviewDTO reviewDTO); // 영화리뷰 추가.

    void modify(ReviewDTO reviewDTO); // 리뷰 수정.

    void remove(Long rvnum); // 리뷰 삭제.

    default ReviewEntity dtoToEntity(ReviewDTO reviewDTO){

        return ReviewEntity.builder()
                .rvnum(reviewDTO.getRvnum())
                .movie(MovieEntity.builder().mno(reviewDTO.getMno()).build())
                .member(MemberEntity.builder().mid(reviewDTO.getMid()).build())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .build();
    }

    default ReviewDTO entityToDto(ReviewEntity review){

        return ReviewDTO.builder()
                .rvnum(review.getRvnum())
                .mno(review.getMovie().getMno())
                .mid(review.getMember().getMid())
                .nickname(review.getMember().getNickname())
                .email(review.getMember().getEmail())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();
    }

}
