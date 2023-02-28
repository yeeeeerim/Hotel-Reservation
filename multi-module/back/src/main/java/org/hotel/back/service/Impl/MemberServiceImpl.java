package org.hotel.back.service.Impl;

import lombok.RequiredArgsConstructor;
import org.hotel.back.data.request.RegisterData;
import org.hotel.back.data.response.HotelAndReviewDTO;
import org.hotel.back.data.response.HotelResponseDTO;
import org.hotel.back.data.response.ReviewResponseDTO;
import org.hotel.back.domain.Gender;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Member;
import org.hotel.back.domain.MemberRole;
import org.hotel.back.repository.MemberRepository;
import org.hotel.back.repository.custom.ManagerRepository;
import org.hotel.back.service.MemberService;
import org.hotel.back.service.api.KaKaoAPIService;
import org.json.simple.parser.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final ManagerRepository managerRepository;

    private final KaKaoAPIService kaKaoAPIService;


    /**
     * @param registerData 회원가입에 사용한 데이터
     * @return true/false 성공시 true 실패시 false
     *
     * */
    public boolean registerSave(RegisterData registerData){


        Member member = Member.builder()
                .email(registerData.getEmail())
                .password(passwordEncoder.encode(registerData.getPassword()))
                .gender(registerData.getGender().equals("MAN") ? Gender.MAN : Gender.WOMAN)
                .nickName(registerData.getNickName())
                .tellNumber(registerData.getTellNumber())
                .build();
        member.addRole(MemberRole.ROLE_USER);

        boolean fail = ObjectUtils.isEmpty(memberRepository.save(member));

        return !fail;
    }

    /**
     * @param email
     * @return true/false 있다면 true 없다면 false
     * */
    public boolean checkEmail(String email){
        return memberRepository.existsByEmail(email);
    }




    /**
     * @param email 해당 작성자가 작성한 호텔정보/리뷰 같이 조회함
     * @return HotelAndReviewDTO Type
     * */
    @Transactional(readOnly = true)
    public HotelAndReviewDTO getHotelAndReviewWithRoom(String email) throws ParseException {

        var hotelData = managerRepository.getHotelInfo(email);
        if (hotelData.isPresent()){
            Hotel hotel = hotelData.get();

            Optional<String> addressInfo =
                    kaKaoAPIService.getAddressInfo(hotel.getLongitude(), hotel.getLatitude());

            return HotelAndReviewDTO.builder()
                    .hotelResponseDTO(HotelResponseDTO.builder()
                            .id(hotel.getId())
                            .hotelName(hotel.getHotelName())
                            .cityName(hotel.getCityName())
                            .tellNumber(hotel.getTellNumber())
                            .latitude(hotel.getLatitude())
                            .address(addressInfo.orElse("정확하지 않은 주소"))
                            .longitude(hotel.getLongitude())
                            .build())
                    .reviewResponseDTO(hotel.getReviews().stream()
                            .map(ReviewResponseDTO::new)
                            .collect(Collectors.toList()))
                    .images(hotel.getHotelImages().stream().map(hotelImage ->
                            hotelImage.getName())
                            .collect(Collectors.toList()))
                    .build();
        }else{
            return null;
        }
    }

}