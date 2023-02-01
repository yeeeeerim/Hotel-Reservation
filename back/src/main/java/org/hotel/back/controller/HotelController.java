package org.hotel.back.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.domain.Hotel;

import org.hotel.back.domain.Review;
import org.hotel.back.dto.request.HotelRequestDTO;
import org.hotel.back.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    @GetMapping("/hotel/save")//localhost:8080/save
    public String hotelWriteForm(){

        return "hotelSave";
    }

    @PostMapping("/hotel/save")
    public String hotelSave(HotelRequestDTO hotelRequestDTO){
        hotelService.write(hotelRequestDTO);
        return "redirect:/hotel";
    }

    @GetMapping("/hotel")
    public String hotelList(@PageableDefault(page = 0, size = 10,sort="id",direction = Sort.Direction.ASC) Pageable pageable, Model model){
        //서비스에서 생성한 리스트를 list라는 이름으로 반환하겠다.
        Page<Hotel> list =hotelService.hotelList(pageable);
        int nowPage =list.getPageable().getPageNumber()+1;//pageable은 0부터 시작해서 +1을 해줘야 함
        int startPage=Math.max(nowPage-4,1);//1보다 작은 수면 1을 반환
        int endPage=Math.min(nowPage+5,list.getTotalPages());


        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        model.addAttribute("list",list);
        return "hotelMain";
    }
    @GetMapping("/hotel/detail")
    public String hotelDetail(Model model, Long id){
        Hotel hotel=hotelService.hotelDetail(id);
        List<Review> reviewlist =hotel.getReviews();
        model.addAttribute("article",hotel);
        model.addAttribute("review",reviewlist);
        return "hotelDetail";
    }

    @GetMapping("/hotel/delete")
    public String hotelDelete(Long id){
        hotelService.hotelDelete(id);
        return "redirect:/hotel";
    }
    @PostMapping("/hotel/update")
    public String hotelUpdatePost(HotelRequestDTO hotelRequestDTO){
        hotelService.hotelUpdate(hotelRequestDTO);
        return "redirect:/hotel/detail?id="+hotelRequestDTO.getId();
    }

    @GetMapping("/hotel/update")
    public String hotelUpdate(Long id, Model model){
        model.addAttribute("article",hotelService.hotelDetail(id));
        return "hotelUpdate";
    }
}
