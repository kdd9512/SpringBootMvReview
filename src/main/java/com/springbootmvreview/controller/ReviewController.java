package com.springbootmvreview.controller;

import com.springbootmvreview.dto.ReviewDTO;
import com.springbootmvreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno){

        log.info("====================list=====================");
        log.info("mno : " + mno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO) {
        log.info("====================add review======================");
        log.info("reviewDTO : " + reviewDTO);

        Long rvnum = reviewService.register(reviewDTO);

        return new ResponseEntity<>(rvnum, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{rvnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long rvnum,
                                             @RequestBody ReviewDTO reviewDTO){
        log.info("=======================modify Review===========================");
        log.info("reviewDTO : " + reviewDTO);

        reviewService.modify(reviewDTO);

        return new ResponseEntity<>(rvnum, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{rvnum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long rvnum){

        log.info("==========================remove Review=============================");
        log.info("rvnum : " + rvnum);

        reviewService.remove(rvnum);

        return new ResponseEntity<>(rvnum, HttpStatus.OK);
    }


}
