package com.back.bookingmodule.service.impl;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.repository.BookingRepository;
import com.back.bookingmodule.service.BookingService;
import lombok.RequiredArgsConstructor;

import java.awt.print.Book;
import java.util.Optional;


@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {


        private BookingRepository bookingRepository;


        /**
         * @apiNote 엔티티를 저장
         * @param  dto BookingDTO를 받고 엔티티로 변환함
         * */
        public void bookingSave(BookingDTO dto){
                bookingRepository.save(dto.toEntity());
        }

        /**
        * @apiNote id(PK)로 해당 엔티티 단건 조회
        *
        * */
         public Optional<BookingDTO> findById(Long id){
                Booking bookingEntity =  bookingRepository.findById(id).orElse(null);

                if(bookingEntity != null){
                        return Optional.of(BookingDTO.toDTO(bookingEntity));
                }else{
                        return Optional.empty();
                }
         }


         //TODO: UPDATE 진행 중
         public void bookingChangeInAndOut(String checkIn,String checkout,String email,Long id){


                if(findById(id).isPresent()){

                }
         }


         //TODO: 리스트조회, 삭제


}
