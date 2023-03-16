package com.back.bookingmodule.data.Response;

import com.back.bookingmodule.config.exception.BookingErrorCode;
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
