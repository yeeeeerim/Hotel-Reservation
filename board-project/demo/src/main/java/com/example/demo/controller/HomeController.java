package com.example.demo.controller;

import com.example.demo.domain.HealthInfo;
import com.example.demo.repository.HealthRepository;
import com.example.demo.service.HealthService;
import com.example.demo.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final HealthRepository healthRepository;
    private final HomeService homeService;
    private final HealthService healthService;

    @GetMapping("/write")
    public String boardWriteForm() {
        return "save";
    }

    @GetMapping("/detail")
    public String healthInfoDetail(Model model, Long id){
        model.addAttribute("HealthInfo",
                healthService.healthInfoDetail(id));
        return "detail";
    }
    @PostMapping("/write/post")
    public String HealthWrite(HealthInfo healthInfo){
        healthRepository.save(HealthInfo.builder()
                .brand_name(healthInfo.getBrand_name())
                .category(healthInfo.getCategory())
                .land_number(healthInfo.getLand_number())
                .road_number(healthInfo.getRoad_number())
                .build());
        return "redirect:/home";
    }
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.ASC)Pageable pageable){
        Page<HealthInfo> list = homeService.healthList(pageable);
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        list.toList().forEach(System.out::println);
        return "home";
    }
}
