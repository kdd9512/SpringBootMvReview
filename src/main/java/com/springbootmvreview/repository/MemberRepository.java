package com.springbootmvreview.repository;

import com.springbootmvreview.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
