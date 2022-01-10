package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

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

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){

        Long mid = 2L; // MemberEntity 의 mid

        MemberEntity member = MemberEntity.builder().mid(mid).build();

        /**
         * 1. FK(외래키) 가 존재하는 Review 쪽을 먼저 삭제해야 한다
         *      (member 에서 PK 를 참조하고 있기 때문에 PK 를 먼저 삭제하는 것은 불가능).
         * 2. 트랜잭션 관련 처리가 필요하다.
         *      (member 가 삭제되면 review 가 삭제되는 것이 목표이므로 두 작업이 동시에 일어나야 함).
         * **/

        reviewRepository.deleteByMember(member); // 삭제된 member 가 작성한 review 삭제.
        memberRepository.deleteById(mid); // 멤버를 삭제.

    }
}
