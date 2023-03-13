package com.back.bookingmodule.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookingErrorCode {
    BOOKING_SAVE_FAIL("저장 실패"),
    BOOKING_UPDATE_FAIL("수정 실패"),
    BOOKING_DELETE_FAIL("삭제 실패"),
    BOOKING_NOT_FOUND("조회 실패"),
    BOOKING_NOT_CHANGE("기존 예약 내용과 같음");
    private final String message;


}
