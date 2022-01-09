package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.MovieImgEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
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

    @Test
    public void testListPage(){
        PageRequest pageRequest = PageRequest.of(0,10,
                Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testGetMovieWithAll(){

        List<Object[]> result = movieRepository.getMovieWithAll(94L);

        System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }

    }
}
