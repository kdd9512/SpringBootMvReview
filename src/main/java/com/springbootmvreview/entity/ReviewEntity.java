package com.springbootmvreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"}) // 자동으로 override 되는 부분을 제외. 이걸 제외안하면 서로 연관관계에 있는 컬럼을 계속 불러와 무한루프가 발생한다.
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

    public void changeGrade(int grade){
        this.grade = grade;
    }

    public void changeText(String text){
        this.text = text;
    }


}
