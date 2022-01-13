package com.springbootmvreview.service;

import com.springbootmvreview.dto.MovieDTO;
import com.springbootmvreview.dto.MovieImgDTO;
import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.MovieImgEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) { // Map 타입으로 반환.

        Map<String, Object> entityMap = new HashMap<>();

        MovieEntity movie = MovieEntity.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();
        entityMap.put("movie", movie);

        List<MovieImgDTO> imageDTOList = movieDTO.getImageDTOList();

        // MovieImgDTO 처리
        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<MovieImgEntity> movieImgList = imageDTOList.stream()
                    .map(movieImgDTO -> {
                        MovieImgEntity movieImg = MovieImgEntity.builder()
                                .path(movieImgDTO.getPath())
                                .imgName(movieImgDTO.getImgName())
                                .uuid(movieImgDTO.getUuid())
                                .movie(movie)
                                .build();
                        return movieImg;
                    }).collect(Collectors.toList());

            entityMap.put("imgList", movieImgList);
        }

        return entityMap;
    }
}
