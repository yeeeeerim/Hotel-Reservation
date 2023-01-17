package com.example.demo.controller;

import com.example.demo.domain.HealthInfo;
import com.example.demo.repository.HealthRepository;

import com.example.demo.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
//    private ReviewRepository reviewRepository;
//    public List<Review> reviewList(){
//        return reviewRepository.findAll();
//    }@@


    @GetMapping("/write")
    public String boardWriteForm() {
        return "save";
    }

    @GetMapping("/list")
    public String healthInfoList(Model model){
        model.addAttribute("list", healthRepository.findAll());
        return "home";
    }
//    @GetMapping("/review")
//    public String healthreview(Model model, Integer id){
//        model.addAttribute("list", reviewRepository.findAll());
//        return "review";
//    }
    @PostMapping("/write/post")
    public String HealthWirte(HealthInfo healthInfo){
        healthRepository.save(HealthInfo.builder()
                .brand_name(healthInfo.getBrand_name())
                .category(healthInfo.getCategory())
                .land_number(healthInfo.getLand_number())
                .road_number(healthInfo.getRoad_number())
                .build());
        return "redirect:/list";
    }
//    @PostMapping("/modify/{id}")
//    public String boardmodify(@PathVariable("id") Long id){
//
//        return "review";
//    }
}
