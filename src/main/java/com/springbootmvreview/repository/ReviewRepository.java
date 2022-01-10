package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MemberEntity;
import com.springbootmvreview.entity.MovieEntity;
import com.springbootmvreview.entity.ReviewEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    /**
     * @EntityGraph 의 속성.
     *
     * 1. attributePaths : 로딩 설정을 변경하고 싶은 속성의 이름을 배열로 명시.
     * 여기서는 ReviewEntity 와 join 하고자 하므로 ReviewEntity 에서 join 할 member 를 object 에 넣는다.
     *
     * 2. type : @EntityGraph 를 적용할 방식을 결정. attributePaths 에 명시한 속성은 두 종류 다 EAGER 로 처리한다.
     *  2-1. EntityGraphType.FETCH : 나머지는 LAZY 로 처리.
     *  2-2. EntityGraphType.LOAD : 나머지는 Entity 클래스에 명시되거나 기본방식으로 처리.
     *
     * **/

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ReviewEntity> findByMovie(MovieEntity movie);

    @Modifying
    @Query("delete from ReviewEntity rv where rv.member = :member")
    void deleteByMember(MemberEntity member);
}
