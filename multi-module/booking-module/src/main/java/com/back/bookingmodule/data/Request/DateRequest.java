package com.back.bookingmodule.data.Request;

import com.back.bookingmodule.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class DateRequest {
    private int year;

    private int month;

    private int day;
}
