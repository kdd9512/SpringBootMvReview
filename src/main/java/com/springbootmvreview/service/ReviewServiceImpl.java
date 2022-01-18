package com.springbootmvreview.service;

import com.springbootmvreview.dto.ReviewDTO;
import com.springbootmvreview.entity.ReviewEntity;
import com.springbootmvreview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {
        return null;
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        return null;
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {

    }

    @Override
    public void remove(Long rvnum) {

    }

    @Override
    public ReviewEntity dtoToEntity(ReviewDTO reviewDTO) {
        return ReviewService.super.dtoToEntity(reviewDTO);
    }

    @Override
    public ReviewDTO entityToDto(ReviewEntity review) {
        return ReviewService.super.entityToDto(review);
    }
}
