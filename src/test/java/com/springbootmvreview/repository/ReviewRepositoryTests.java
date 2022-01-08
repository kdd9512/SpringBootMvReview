package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MemberEntity;
import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


}
