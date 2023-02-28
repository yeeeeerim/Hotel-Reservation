package org.hotel.back.service.Impl;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.hotel.back.config.exception.FileUploadException;
import org.hotel.back.data.response.HotelListResponseDTO;
import org.hotel.back.data.response.HotelResponseDTO;
import org.hotel.back.domain.Hotel;
import org.hotel.back.data.request.HotelRequestDTO;
import org.hotel.back.domain.HotelImage;
import org.hotel.back.repository.HotelImageRepository;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.service.HotelService;
import org.hotel.back.service.api.KaKaoAPIService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelImageRepository hotelImageRepository;
    private final KaKaoAPIService kaKaoAPIService;
    @Value("${upload.path}")
    private String path;

    //호텔 저장
    @Override
    public boolean write(HotelRequestDTO hotelRequestDTO) {
        if(hotelRequestDTO.getHotelImage().isEmpty()){ //첨부파일 없음
            Hotel hotel=hotelRequestDTO.toEntity(hotelRequestDTO);
            //요청 데이터를(hotelRequestDTO) hotel객체로 바꿔준다.
            hotelRepository.save(hotel);
            System.out.println("첨부파일 없음 ");
        }
        else{
            Hotel hotel=hotelRequestDTO.toEntity(hotelRequestDTO);
            Long hotelId=hotelRepository.save(hotel).getId();

            //썸네일
            for(MultipartFile hotelImage:hotelRequestDTO.getHotelImage()){
                String uuid = UUID.randomUUID().toString()+"_"+hotelImage.getOriginalFilename();
                Path savePath = Paths.get(path, uuid);
                try{
                    hotelImage.transferTo(savePath);
                } catch (IOException e) {
                    throw new FileUploadException();
                }
                Hotel findHotel=hotelRepository.findById(hotelId).get();
                HotelImage hotelImageSave=HotelImage.builder().name(uuid).hotel(findHotel).build();
                hotelImageRepository.save(hotelImageSave);
            }
        }
        return true;
    }
    //호텔 리스트
    @Override
    public Page<HotelListResponseDTO> hotelList(Pageable pageable) {
        Page hotel=hotelRepository.findAll(pageable);

        return hotel;
    }
    //호텔 자세히보기
    @Override
    public HotelResponseDTO hotelDetail(Long id) throws ParseException {
        List<Object[]> result = hotelRepository.getHotelWithAll(id);
        Hotel hotel=(Hotel)result.get(0)[0];
        Double avg=(Double) result.get(0)[1]; //평점
        HotelResponseDTO hotelResponseDTO=new HotelResponseDTO(hotel,avg);
        if(kaKaoAPIService.getAddressInfo(hotel.getLongitude(),hotel.getLatitude()).isPresent()){//위, 경도를 넣어서 주소가 반환된다면
            String address=kaKaoAPIService.getAddressInfo(hotelResponseDTO.getLongitude(),hotelResponseDTO.getLatitude()).orElse(null);//address에 값 넣기
            hotelResponseDTO.setAddress(address);//변환한 주소 넣기
        }
        return hotelResponseDTO;
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

    @Override
    public List<HotelResponseDTO> hotelListSearch(String keyword) {
        List<Hotel> hotels = hotelRepository.findByHotelNameContaining(keyword);
        return null;
    }
}
