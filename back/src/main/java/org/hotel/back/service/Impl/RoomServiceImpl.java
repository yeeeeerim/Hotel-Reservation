package org.hotel.back.service.Impl;


import lombok.RequiredArgsConstructor;
import org.hotel.back.config.exception.FileUploadException;
import org.hotel.back.config.exception.FileViewException;
import org.hotel.back.data.dto.FileDTO;
import org.hotel.back.data.dto.UploadDTO;
import org.hotel.back.data.response.FileResponseData;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.data.response.RoomResponseDTO;
import org.hotel.back.domain.Room;
import org.hotel.back.domain.RoomImage;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.RoomService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {


    @Value("${upload.path}")
    private String path;

    private final RoomRepository roomRepository;


    public List<FileDTO> upload(UploadDTO uploadDTO){
        if(!uploadDTO.getFiles().isEmpty()){

            final List<FileDTO> list = new ArrayList<>();

            uploadDTO.getFiles().forEach(multipartFile -> {
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(path, uuid+"_"+multipartFile.getOriginalFilename());

                try{
                    multipartFile.transferTo(savePath);
                } catch (IOException e) {
                    throw new FileUploadException();
                }

                list.add(FileDTO.builder()
                                .uuid(uuid)
                                .fileName(multipartFile.getOriginalFilename())
                        .build());
            });
            return list;
        }
        return null;
       }

       public FileResponseData viewFile(String fileName){
            Resource resource = new FileSystemResource(path+ File.separator+fileName);

            String resourceName = resource.getFilename();
            HttpHeaders headers = new HttpHeaders();

            try{
                headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
            } catch (IOException e){
                throw new FileViewException();
            }
            return FileResponseData.builder().headers(headers).resource(resource).build();

       }

        public Map<String,Boolean> removeFile(String fileName){
                Resource resource = new FileSystemResource(path + File.separator + fileName);
                String resourceName = resource.getFilename();

                Map<String, Boolean> resultMap = new HashMap<>();
                boolean removed = false;


                try{
                    String contentType = Files.probeContentType(resource.getFile().toPath());
                    removed = resource.getFile().delete();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                resultMap.put("result", removed);
                return resultMap;
        }




       public void save(RoomDTO roomDTO){
                roomRepository.save(toEntity(roomDTO));
       }
       public RoomDTO findByRoomWithImage(Long id){
                return toDTO(roomRepository.getRoomWithImage(id).orElseThrow(RuntimeException::new));
       }

        public boolean modifyRoom(RoomDTO roomDTO){
                Room room = roomRepository.getRoomWithImage(roomDTO.getId()).orElse(null);

                if(room != null){
                    room.changeRoomInfo(roomDTO.getRoomPrice(), roomDTO.getDescription());
                    room.imageInit();

                    if(roomDTO.getFileNames() != null){
                        for(String fileName : roomDTO.getFileNames()){
                            room.addImage(fileName);
                        }
                    }

                    roomRepository.save(room);
                    return true;
                }else{
                    return false;
                }
        }

        public void deleteRoom(Long id){
            roomRepository.deleteById(id);  //상세보기 페이지에서 id값을 날리기 때문에 굳이 null체크 필요없을 거 같다
        }



    protected Room toEntity(RoomDTO dto){
                Room room = Room.builder()
                        .hotelId(Long.parseLong(dto.getHotelId()))
                        .description(dto.getDescription())
                        .roomPrice(dto.getRoomPrice())
                        .roomLimit(dto.getRoomLimit())
                        .roomNumber(dto.getRoomNumber())
                        .roomClass(dto.getRoomClass())
                        .build();

                if(dto.getFileNames() != null){
                    dto.getFileNames().forEach(room::addImage);
                   }
                return room;
     }
     protected RoomDTO toDTO(Room room) {
         RoomDTO dto = RoomDTO.builder()
                 .id(room.getId())
                 .description(room.getDescription())
                 .roomPrice(room.getRoomPrice())
                 .roomLimit(room.getRoomLimit())
                 .roomNumber(room.getRoomNumber())
                 .roomClass(room.getRoomClass())
                 .hotelId(Long.toString(room.getHotelId()))
                 .build();

         dto.setFileNames(room.getRoomImage()
                 .stream()
                 .sorted()
                 .map(RoomImage::getName)
                 .collect(Collectors.toList()));
         return dto;
     }

     public RoomResponseDTO getDetail(long id){
         Room room = roomRepository.getRoomWithImage(id).orElse(null);
         if(room != null){
           RoomResponseDTO roomResponseDTO =  RoomResponseDTO.builder()
                     .id(room.getId())
                     .description(room.getDescription())
                     .roomClass(room.getRoomClass())
                     .roomLimit(room.getRoomLimit())
                     .hotelId(room.getHotelId())
                     .roomNumber(room.getRoomNumber())
                     .roomImage(room.getRoomImage()
                             .stream()
                             .map(RoomImage::getName)
                             .collect(Collectors.toUnmodifiableSet()))
                     .roomPrice(room.getRoomPrice())
                     .build();
           return roomResponseDTO;
         }else{
             return RoomResponseDTO.builder().build();
         }
    }




    public List<RoomResponseDTO> findAllWithImage(Long id){
            List<RoomResponseDTO> responseDTOList = new ArrayList<>();


            roomRepository.roomListWithImage(id).forEach(room -> {
                responseDTOList.add(RoomResponseDTO.builder()
                                .roomPrice(room.getRoomPrice())
                                .roomLimit(room.getRoomLimit())
                                .roomClass(room.getRoomClass())
                                .roomNumber(room.getRoomNumber())
                                .id(room.getId())
                                .roomImage(room.getRoomImage().stream()
                                        .map(image -> image.getName())
                                        .collect(Collectors.toUnmodifiableSet()))
                                .hotelId(room.getHotelId())
                                .description(room.getDescription())
                        .build());
            });

            return responseDTOList;
    }


    /**
     * @apiNote 호텔정보 작성자와 해당 사용자가 같은지 조회 하는 기능
     * @param hotelId, email
     *
     * */
    public boolean hotelInfoWriter(Long hotelId, String email){
         return false;
    }






}
