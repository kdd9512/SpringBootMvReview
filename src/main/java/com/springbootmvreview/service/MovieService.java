package com.springbootmvreview.service;

import com.springbootmvreview.dto.MovieDTO;
import com.springbootmvreview.dto.MovieImgDTO;
import com.springbootmvreview.dto.PageRequestDTO;
import com.springbootmvreview.dto.PageResultDTO;
import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.MovieImgEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);

    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO); // 목록 처리

    default MovieDTO entitiesToDTO(MovieEntity movie, List<MovieImgEntity> movieImages,
                                   Double avg, Long reviewCnt) {
        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();

        List<MovieImgDTO> movieImgDTOList = movieImages.stream()
                .map(movieImgEntity -> {
                    System.out.println(movieImgEntity);
                            return MovieImgDTO.builder()
                                    .imgName(movieImgEntity.getImgName())
                                    .path(movieImgEntity.getPath())
                                    .uuid(movieImgEntity.getUuid())
                                    .build();
                        }
                ).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImgDTOList);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt.intValue());

        return movieDTO;
    }

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) { // Map 타입으로 반환.

        Map<String, Object> entityMap = new HashMap<>();

        MovieEntity movie = MovieEntity.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);

        // register 에서 입력받아 생성할 hidden 태그들의 name 과 동일해야 한다.
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
