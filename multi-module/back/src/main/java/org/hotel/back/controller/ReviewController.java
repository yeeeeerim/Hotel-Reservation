package org.hotel.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.request.ReviewRequestDTO;
import org.hotel.back.data.response.ReviewResponseDTO;
import org.hotel.back.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/hotel/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> reviewDelete(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    // 댓글 작성
    @PostMapping("/{hotelId}")
    public ResponseEntity<Void> reviewSave(@PathVariable("hotelId") Long hotelId, @RequestBody ReviewRequestDTO reviewRequestDTO) {
        reviewService.saveReview(hotelId, reviewRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 댓글 수정
    @PutMapping
    public ResponseEntity<Void> reviewUpdate( @RequestBody ReviewRequestDTO reviewRequestDTO) {
        reviewService.updateReview(reviewRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> reviewUpdateGet(@PathVariable Long id) {
        ReviewResponseDTO review=reviewService.readReview(id);
        return ResponseEntity.ok(review);
    }

}
