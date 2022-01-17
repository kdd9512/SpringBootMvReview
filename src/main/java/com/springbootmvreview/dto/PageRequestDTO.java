package com.springbootmvreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;
    private int size;
    private String type;
    private String keyword;

    // 최초 시작할 페이지(page)와 한 페이지에 비출 최대 데이터 컬럼의 수(size)를 결정.
    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {

        return PageRequest.of(page -1, size, sort);

    }
}
