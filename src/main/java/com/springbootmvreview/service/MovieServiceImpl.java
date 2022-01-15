package com.springbootmvreview.service;

import com.springbootmvreview.dto.MovieDTO;
import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.MovieImgEntity;
import com.springbootmvreview.repository.MovieImgRepository;
import com.springbootmvreview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieImgRepository imgRepository;

//    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        MovieEntity movie = (MovieEntity) entityMap.get("movie");
        List<MovieImgEntity> movieImgList = (List<MovieImgEntity>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImgList.forEach(movieImgEntity -> {
            imgRepository.save(movieImgEntity);
        });

        return movie.getMno();
    }
}
