package org.hotel.back.config.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingErrorResponse {
    private BookingErrorCode errorCode;
    private String errorMessage;
}