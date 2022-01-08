package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MovieImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImgRepository extends JpaRepository<MovieImgEntity, Long> {
}
