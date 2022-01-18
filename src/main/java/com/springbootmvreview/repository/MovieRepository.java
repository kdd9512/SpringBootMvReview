package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query("select m, mi, avg(coalesce(r.grade,0)), count(r) from MovieEntity m " +
            "left outer join MovieImgEntity mi on mi.movie = m " +
            "left outer join ReviewEntity r on r.movie = m group by m ")
    Page<Object[]> getListPage(Pageable pageable); // 페이지 처리용 jpql

    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r) " +
            "from MovieEntity m left outer join MovieImgEntity mi on mi.movie = m " +
            "left outer join ReviewEntity r on r.movie = m " +
            "where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(Long mno); // 특정 영화 조회.


}
