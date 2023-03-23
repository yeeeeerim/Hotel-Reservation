package org.hotel.back.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.config.exception.FileUploadException;
import org.hotel.back.config.exception.HotelNotFoundException;
import org.hotel.back.data.response.HotelImageDTO;
import org.hotel.back.data.response.HotelListResponseDTO;
import org.hotel.back.data.response.HotelResponseDTO;
import org.hotel.back.domain.Hotel;
import org.hotel.back.data.request.HotelRequestDTO;
import org.hotel.back.domain.HotelImage;
import org.hotel.back.domain.Review;
import org.hotel.back.repository.HotelImageRepository;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.HotelImageCacheService;
import org.hotel.back.service.HotelService;
import org.hotel.back.service.api.KaKaoAPIService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelImageRepository hotelImageRepository;
    private final KaKaoAPIService kaKaoAPIService;
    private final HotelImageCacheService hotelCacheService;
    @Value("${upload.path}")
    private String path;

    /**
     * @apiNote
     * 호텔정보 저장: 첨부파일이 있으면 따로 hotelImage에 저장(이미지 이름)
     * */
    @Override
    public boolean write(HotelRequestDTO hotelRequestDTO) {
        Hotel hotel = hotelRequestDTO.toEntity(hotelRequestDTO);
        hotelRepository.save(hotel);
        saveHotelImages(hotelRequestDTO.getHotelImage(), hotel);
        return true;
    }


    /***
     *
     * @apiNote
     * 호텔리스트: 호텔 이미지가 있으면 첫번째꺼를 가져와 리스트에 넣는다.
     */
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



    /**
     * @apiNote
     * 호텔 자세히보기: 호텔 정보와 리뷰에 있는 평점을 가져와 계산한다.(만약 평점이 없을 경우 0을 반환한다.)
     * DB에 있는 위도와 경도를 주소로 변환해 DTO에 담는다.
     **/
    @Override
    public HotelResponseDTO hotelDetail(Long id) throws ParseException {
        Hotel hotel=hotelRepository.findFetchJoin(id);

        List<Long> rating = hotel.getReviews()
                .stream()
                .map(review -> review.getRating())
                .filter(Objects::nonNull) // null이 아닌 요소만 필터링합니다
                .collect(Collectors.toList());

        System.out.println(rating);

        double avg=0;
        //리뷰 평점계산
        if(rating.size()!=0){
            for (Long a:rating) {
                avg+=a;
            }
            avg/=rating.size();
        }
        HotelResponseDTO dto=new HotelResponseDTO(hotel,avg);
        var data = kaKaoAPIService.getAddressInfo(hotel.getLongitude(),hotel.getLatitude());
        if(data.isPresent()){//위, 경도를 넣어서 주소가 반환된다면
            String address = data.get();
            dto.setAddress(address);//변환한 주소 넣기
        }

        return dto;

    }

    /**
     * @apiNote
     * 호텔 삭제
     */
    @Override
    public boolean hotelDelete(Long id) {
        hotelRepository.deleteById(id);
        return true;
    }

	/***
	 *
	 * @apiNote
	 * 호텔 업데이트: getHotelById메소드로 수정정보가 DB에 있는지 확인-> 수정 ->이미지 수정
	 */
	@Override
    public boolean hotelUpdate(HotelRequestDTO hotelRequestDTO) {
        Hotel hotel = getHotelById(hotelRequestDTO.getId());
        hotel.modifyHotel(hotelRequestDTO.getHotelName(),
                hotelRequestDTO.getCityName(),
                hotelRequestDTO.getTellNumber(),
                hotelRequestDTO.getLatitude(),
                hotelRequestDTO.getLongitude());

        saveHotelImages(hotelRequestDTO.getHotelImage(), hotel);

        hotelRepository.save(hotel);
        return true;
    }

    /***
     *
     * @apiNote
     * id로 호텔찾기
     */
    private Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id " + hotelId));
    }

	/**
	 * @apiNote
	 * 호텔이미지 저장
	 * */
    private void saveHotelImages(List<MultipartFile> hotelImages, Hotel hotel) {
        if (hotel.getHotelImages() != null && !hotelImages.isEmpty()) {
            for (MultipartFile hotelImage : hotelImages) {
                if (!hotelImage.isEmpty()) {
                    String uuid = UUID.randomUUID().toString() + "_" + hotelImage.getOriginalFilename();
                    Path savePath = Paths.get(path, uuid);
                    try {
                        hotelImage.transferTo(savePath);

                    } catch (IOException e) {
                        throw new FileUploadException("Failed to save hotel image");
                    }
                    HotelImage hotelImageSave = HotelImage.builder().name(uuid).hotel(hotel).build();
                    hotelImageRepository.save(hotelImageSave);
                }
            }
        }
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
    @Override
    @Transactional
    public HotelImageDTO findByHotelImage(Long id) throws JsonProcessingException {
        HotelImageDTO hotelImageDTO=hotelCacheService.findById(id);
        if(hotelImageDTO==null){
            HotelImageDTO temp=toDTO(id,hotelImageRepository.findHotelImageByHotel_Id(id));
            hotelCacheService.save(temp);
            System.out.println("레퍼지토리 저장");
            return temp;
        }
        System.out.println("캐시에 저장");
        return hotelImageDTO;
    }

    protected HotelImageDTO toDTO(Long id, List<HotelImage>hotelImages){
        if(!hotelImages.isEmpty()||hotelImages!=null){
            HotelImageDTO dto= HotelImageDTO.builder()
                    .hotelId(id)
                    .name(hotelImages.stream().map(HotelImage::getName).collect(Collectors.toList()))
                    .build();
            return dto;

        }
        return null;

    }
}
