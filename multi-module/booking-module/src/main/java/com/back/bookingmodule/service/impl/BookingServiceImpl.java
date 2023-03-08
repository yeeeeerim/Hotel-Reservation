package com.back.bookingmodule.service.impl;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.domain.Member;
import com.back.bookingmodule.repository.BookingRepository;
import com.back.bookingmodule.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {


        private BookingRepository bookingRepository;


        /**
         * @apiNote 엔티티를 저장
         * @param  dto BookingDTO를 받고 엔티티로 변환함
         * */
        public Booking bookingSave(BookingDTO dto){
            return bookingRepository.save(dto.toEntity());
        }

        /**
         * @apiNote id(PK)로 해당 엔티티 단건 조회
         */
         public BookingDTO findById(Long id){
             return BookingDTO.toDTO(bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found id data")));
         }


         /**
          * SecurityContextHolder 사용해서 로그인 정보 id 값인 memberemail과 파라미터로 전달받은 memberEmail이 같을 경우 updateBooking 실행
          * */
         public void updateBooking(String checkIn, String checkout, String memberEmail, Long id){
             if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(memberEmail)){
                 bookingRepository.updateBooking(id, checkIn, checkout);
             }
         }


         //TODO: 리스트조회, 삭제

        public List<Booking> getBooking(){
             return bookingRepository.findAll();
        }
        public void delete(Long id){
             bookingRepository.deleteById(id);
        }

}
