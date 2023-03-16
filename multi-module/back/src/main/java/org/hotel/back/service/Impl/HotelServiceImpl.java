package org.hotel.back.service.Impl;

import lombok.RequiredArgsConstructor;
import org.hotel.back.config.exception.FileUploadException;
import org.hotel.back.data.response.HotelListResponseDTO;
import org.hotel.back.data.response.HotelResponseDTO;
import org.hotel.back.domain.Hotel;
import org.hotel.back.data.request.HotelRequestDTO;
import org.hotel.back.domain.HotelImage;
import org.hotel.back.domain.Review;
import org.hotel.back.repository.HotelImageRepository;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.service.HotelService;
import org.hotel.back.service.api.KaKaoAPIService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Page<Hotel> hotels = hotelRepository.findAll(pageable);
        List<HotelListResponseDTO> listDTO = new ArrayList<>();
        //호텔이미지가 있으면 dto에 호텔 대표이미지 담기
        for (Hotel h : hotels) {
            HotelListResponseDTO dto = new HotelListResponseDTO(h);
            List<HotelImage> hotelImages = h.getHotelImages();
            if (hotelImages != null && !hotelImages.isEmpty()) {
                dto.setHotelImage(hotelImages.get(0).getName());
            }
            listDTO.add(dto);
        }
        return new PageImpl<>(listDTO, pageable, hotels.getTotalElements());
    }


    //호텔 자세히보기
    @Override
    public HotelResponseDTO hotelDetail(Long id) throws ParseException {
        Hotel hotel=hotelRepository.findFetchJoin(id);

        List<Long> rating=hotel.getReviews().stream().map(review -> review.getRating()).collect(Collectors.toList());
        //리뷰 평점계산
        double avg=0;
        for (Long a:rating) {
            avg+=a;
        }
        avg/=rating.size();
        if(Double.isNaN(avg)){avg=0;} //평점이 없어 NaN으로 계산이 된 경우 0으로 값을 변경한다.

        HotelResponseDTO dto=new HotelResponseDTO(hotel,avg);
        var data = kaKaoAPIService.getAddressInfo(hotel.getLongitude(),hotel.getLatitude());
        if(data.isPresent()){//위, 경도를 넣어서 주소가 반환된다면
            String address = data.get();
            dto.setAddress(address);//변환한 주소 넣기
        }

        return dto;

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
        //-----
        System.out.println("-----*****이미지 이름"+hotelRequestDTO.getHotelImage().toString());
        Hotel hotel=hotelRepository.findById(hotelRequestDTO.getId()).get();
        hotel.modifyHotel(hotelRequestDTO.getHotelName(),
                hotelRequestDTO.getCityName(),
                hotelRequestDTO.getTellNumber(),
                hotelRequestDTO.getLatitude(),
                hotelRequestDTO.getLongitude());

        if(!hotelRequestDTO.getHotelImage().isEmpty()) {
            for(MultipartFile hotelImage:hotelRequestDTO.getHotelImage()){
                if(!hotelImage.isEmpty()){
                    String uuid = UUID.randomUUID().toString()+"_"+hotelImage.getOriginalFilename();
                    Path savePath = Paths.get(path, uuid);
                    try{
                        hotelImage.transferTo(savePath);
                    } catch (IOException e) {
                        throw new FileUploadException();
                    }
                    HotelImage hotelImageSave=HotelImage.builder().name(uuid).hotel(hotel).build();
                    hotelImageRepository.save(hotelImageSave);
                }
            }
        }
        hotelRepository.save(hotel);
        return true;
    }


    @Override
    public List<HotelResponseDTO> hotelListSearch(String keyword) {
        List<Hotel> hotels = hotelRepository.findByHotelNameContaining(keyword);
        return null;
    }

    @Override
    public boolean imageDelete(String name) {
        hotelImageRepository.deleteById(name);
        return true;
    }
}
