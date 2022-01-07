package com.springbootmvreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"})
public class ReviewEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rvnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private MovieEntity movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    private int grade;

    private String text;
}
