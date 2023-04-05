package org.hotel.back.config.exception;

import lombok.Getter;

@Getter
public class BookingException extends RuntimeException{
    private BookingErrorCode bookingErrorCode;
    private String detailMessage;

    public BookingException(BookingErrorCode errorCode){ // 만들어놓은 메세지에 맞는 에러코드 발생시
        super(errorCode.getMessage());
        this.bookingErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }
    public BookingException(BookingErrorCode errorCode, String detailMessage){
        super(errorCode.getMessage());
        this.bookingErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}