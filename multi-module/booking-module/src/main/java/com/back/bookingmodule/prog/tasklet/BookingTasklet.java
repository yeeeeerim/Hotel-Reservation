package com.back.bookingmodule.prog.tasklet;


import com.back.bookingmodule.domain.booking.Booking;
import com.back.bookingmodule.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class BookingTasklet implements Tasklet {

    private final BookingRepository bookingRepository;



    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Booking> list = bookingRepository.getBookingByCheckout(LocalDateTime.now());

        list.forEach(booking -> {
            booking.changeDeleting();
        });

        return RepeatStatus.FINISHED;
    }
}
