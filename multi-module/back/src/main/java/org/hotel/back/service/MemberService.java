package org.hotel.back.service;


import lombok.RequiredArgsConstructor;
import org.hotel.back.data.request.RegisterData;
import org.hotel.back.data.response.HotelAndReviewDTO;
import org.hotel.back.domain.Gender;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Member;
import org.hotel.back.domain.MemberRole;
import org.hotel.back.repository.MemberRepository;
import org.hotel.back.repository.custom.ManagerRepository;
import org.json.simple.parser.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;


public interface MemberService {



    /**
     * @param registerData 회원가입에 사용한 데이터
     * @return true/false 성공시 true 실패시 false
     *
     * */
    public boolean registerSave(RegisterData registerData);

    /**
     * @param email
     * @return true/false 있다면 true 없다면 false
     * */
    public boolean checkEmail(String email);




    /**
     * @param email 해당 작성자가 작성한 호텔정보/룸정보/리뷰까지
     * @apiNote 여기서 Hotel과 Review는 fetch join 으로 가져오고
     *              hotelImage는 따로 BatchSize를 통해 가져옴 -> 데이터가 많아봤자 10개 안 넘음
     *
     * */
    public List<HotelAndReviewDTO> getReviewByEmail(String email) throws ParseException;
}
