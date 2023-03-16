package com.back.bookingmodule.service.impl;

import com.back.bookingmodule.config.exception.BookingErrorCode;
import com.back.bookingmodule.config.exception.BookingException;
import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Status;
import com.back.bookingmodule.domain.booking.Booking;
import com.back.bookingmodule.repository.BookingRepository;
import com.back.bookingmodule.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {


        private BookingRepository bookingRepository;


        /**
         * @apiNote 엔티티를 저장
         * @param  dto BookingDTO를 받고 엔티티로 변환함
         * */
        public Booking bookingSave(BookingDTO dto){
            Booking booking = bookingRepository.save(dto.toEntity());
            if (bookingRepository.findById(dto.toEntity().getId()).isEmpty()){
                throw new BookingException(BookingErrorCode.BOOKING_SAVE_FAIL);
            }
            return booking;
        }

        /**
         * @apiNote id(PK)로 해당 엔티티 단건 조회, 조회 실패할 경우 NOT_FOUND Exception 발생
         */
         public BookingDTO findById(Long id){
             return BookingDTO.toDTO(bookingRepository.findById(id)
                     .orElseThrow(() -> new BookingException(BookingErrorCode.BOOKING_NOT_FOUND)));
         }


         /**
          * SecurityContextHolder 사용해서 로그인 정보 id 값인 memberemail과 파라미터로 전달받은 memberEmail이 같을 경우 updateBooking 실행
          * */
         public void updateBooking(String checkIn, String checkout, String memberEmail, Long id){
             if (bookingRepository.findById(id).isEmpty()){
                 throw new BookingException(BookingErrorCode.BOOKING_NOT_FOUND);
             } else if ((checkIn.equals(bookingRepository.findById(id).get().getCheckIn()))
                     && (checkout.equals(bookingRepository.findById(id).get().getCheckOut()))) {
                 throw new BookingException(BookingErrorCode.BOOKING_NOT_CHANGE);
             }else {
                 bookingRepository.updateBooking(id, checkIn, checkout);
             }
         }


         //TODO: 리스트조회, 삭제

        public List<Booking> getBooking(){
             return bookingRepository.findAll();
        }
        public void delete(Long id){
             bookingRepository.deleteById(id);
             if (!(bookingRepository.findById(id).isEmpty())){
                 throw new BookingException(BookingErrorCode.BOOKING_DELETE_FAIL);
             }
        }



}
