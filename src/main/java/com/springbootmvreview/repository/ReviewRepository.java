package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByMovie(MovieEntity movie);
}
