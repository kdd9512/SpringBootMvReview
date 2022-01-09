package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MovieImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieImgRepository extends JpaRepository<MovieImgEntity, Long> {

}
