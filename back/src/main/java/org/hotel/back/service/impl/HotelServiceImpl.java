package org.hotel.back.service.impl;

import lombok.RequiredArgsConstructor;
import org.hotel.back.domain.Hotel;
import org.hotel.back.dto.request.HotelRequestDTO;
import org.hotel.back.dto.response.HotelResponseDTO;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    //호텔 저장
    @Override
    public boolean write(HotelRequestDTO hotelRequestDTO) {
        Hotel hotel=hotelRequestDTO.toEntity(hotelRequestDTO); //요청 데이터를(hotelRequestDTO) hotel객체로 바꿔준다.
        hotelRepository.save(hotel);
        return true;
    }
    //호텔 리스트
    @Override
    public Page<Hotel> hotelList(Pageable pageable) {

        return hotelRepository.findAll(pageable);
    }
    //호텔 자세히보기
    @Override
    public Hotel hotelDetail(Long id) {
        Hotel hotel=hotelRepository.findFetchJoin(id);
        return hotel;
    }
    //호텔 지우기
    @Override
    public boolean hotelDelete(Long id) {
        hotelRepository.deleteById(id);
        return true;
    }
    //호텔 업데이트
    @Override
    public boolean hotelUpdate(HotelRequestDTO hotelRequestDTO) {
        Hotel hotel=hotelRepository.findById(hotelRequestDTO.getId()).get();
        hotel.modifyHotel(hotelRequestDTO.getHotelName(),
                hotelRequestDTO.getCityName(),
                hotelRequestDTO.getTellNumber(),
                hotelRequestDTO.getLatitude(),
                hotelRequestDTO.getLongitude());
        hotelRepository.save(hotel);
        return true;
    }
}
