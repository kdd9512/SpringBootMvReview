package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            MemberEntity member = MemberEntity.builder()
                    .email("r" + i + "@email.com")
                    .pw("1234")
                    .nickname("reviewer" + i)
                    .build();
            memberRepository.save(member);
        });

    }
}
