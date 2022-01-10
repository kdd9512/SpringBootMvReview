package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MemberEntity;
import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertReview() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            // 영화 번호
            Long mno = (long) (Math.random() * 100) + 1;

            // 리뷰어 번호
            Long mid = ((long)(Math.random() * 100) + 1);
            MemberEntity member = MemberEntity.builder().mid(mid).build();

            ReviewEntity mvReview = ReviewEntity.builder()
                    .member(member)
                    .movie(MovieEntity.builder().mno(mno).build())
                    .grade((int)(Math.random() * 5) + 1)
                    .text("Review..." + i)
                    .build();

            reviewRepository.save(mvReview);

        });

    }

    // 92 번 영화의 review 내용을 출력한다.
    @Test
    public void testGetMovieReviews() {

        MovieEntity movie = MovieEntity.builder().mno(92L).build();

        List<ReviewEntity> result = reviewRepository.findByMovie(movie);

        result.forEach(mvReview -> {
            System.out.println(mvReview.getRvnum());
            System.out.println("\t" + mvReview.getGrade());
            System.out.println("\t" + mvReview.getText());
            System.out.println("\t" + mvReview.getMember().getEmail());
            System.out.println("====================================================");
        });
    }


}
