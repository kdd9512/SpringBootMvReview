package com.springbootmvreview.service;

import com.springbootmvreview.dto.ReviewDTO;
import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.ReviewEntity;
import com.springbootmvreview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {

        MovieEntity movie = MovieEntity.builder().mno(mno).build();
        List<ReviewEntity> result = reviewRepository.findByMovie(movie);

        return result.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        ReviewEntity review = dtoToEntity(reviewDTO);
        reviewRepository.save(review);

        return review.getRvnum();
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        Optional<ReviewEntity> result = reviewRepository.findById(reviewDTO.getRvnum());

        if (result.isPresent()) {
            ReviewEntity movieReview = result.get();
            movieReview.changeGrade(reviewDTO.getGrade());
            movieReview.changeText(reviewDTO.getText());

            reviewRepository.save(movieReview);
        }
    }

    @Override
    public void remove(Long rvnum) {
        reviewRepository.deleteById(rvnum);
    }

}
