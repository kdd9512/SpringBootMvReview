package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.MovieImgEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImgRepository imgRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovie() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            MovieEntity movie = MovieEntity.builder().title("Movie..." + i).build();

            System.out.println("--------------------------------------------------");

            movieRepository.save(movie);

            int cnt = (int) (Math.random() * 5) + 1; // 1,2,3,4...

            for (int j = 0; j < cnt; j++) {
                MovieImgEntity mvImage = MovieImgEntity.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();

                imgRepository.save(mvImage);
            }
            System.out.println("==============================================================");
        });
    }
}
