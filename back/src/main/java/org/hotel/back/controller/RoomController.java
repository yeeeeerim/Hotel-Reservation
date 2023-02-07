package org.hotel.back.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.Room;
import org.hotel.back.service.RoomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/room/list")
    public String roomListGET(@RequestParam Long id,Model model){
            model.addAttribute("dto",roomService.findAllWithImage(id));
            return "/room/room-list";
    }


    @GetMapping("/room/detail")
    public String roomDetailsGET(Long id, Model model){
        model.addAttribute("dto",roomService.getDetail(id));
        return "/room/room-detail";
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/room/save")
    public String roomSaveGET(RoomDTO roomDTO){
        return "/room/room-save";
    }


    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/room/modify")
    public String roomModifyGET(Long id,Model model){
        model.addAttribute("dto",roomService.getDetail(id));
        return "/room/room-modify";
    }


    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/room/delete")
    public String roomDeleteGET(RoomDTO dto, RedirectAttributes redirectAttributes){
            redirectAttributes.addAttribute("id",dto.getHotelId());
            roomService.deleteRoom(dto.getId());
        return "redirect:/hotel/detail";
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/room/save")
    public String roomSavePOST(RoomDTO roomDTO){
            log.info("==> {}",roomDTO);
            roomService.save(roomDTO);
             return "redirect:/room/save";
    }
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/room/modify")
    public String roomModifyPOST(RoomDTO roomDTO,RedirectAttributes redirectAttributes){
        roomService.modifyRoom(roomDTO);
        log.info("=>>>>>> {}",roomDTO);
        redirectAttributes.addAttribute("id",roomDTO.getId());
        return "redirect:/room/detail";
    }

}
