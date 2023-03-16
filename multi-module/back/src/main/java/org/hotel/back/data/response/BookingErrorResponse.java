package org.hotel.back.data.response;
import lombok.*;
import org.hotel.back.config.exception.BookingErrorCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingErrorResponse {
	private BookingErrorCode errorCode;
	private String errorMessage;
}
