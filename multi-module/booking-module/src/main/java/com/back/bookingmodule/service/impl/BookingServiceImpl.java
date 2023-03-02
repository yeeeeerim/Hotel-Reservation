package com.back.bookingmodule.service.impl;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.domain.Member;
import com.back.bookingmodule.repository.BookingRepository;
import com.back.bookingmodule.service.BookingService;
import lombok.RequiredArgsConstructor;
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
         public void updateBooking(String checkIn, String checkout, Member member, Long id){
             findById(id).get();
             // booking member fetch join --> member에 이메일과 접근한 사용자에 이메일 비교
             // booking에다가 writer ==> writer 비교하는 거
             // hotel_id 만들어서 쓰는 방법인데 어려우면 안 해도 됨
             bookingRepository.updateBooking(member, id, checkIn, checkout);

         }


         //TODO: 리스트조회, 삭제

        public List<Booking> getBooking(){
             return bookingRepository.findAll();
        }
        public void delete(Long id){ //일단 써놓긴 했는데 bookingRepository에 컨트롤러에서 그냥 deleteById를 바로 사용하는 것랑 차이가 있을까여..?
             bookingRepository.deleteById(id);
        }

}
