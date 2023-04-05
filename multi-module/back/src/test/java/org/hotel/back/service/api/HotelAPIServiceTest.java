package org.hotel.back.service.api;




class HotelAPIServiceTest {



<<<<<<< HEAD
=======
    @Autowired
    HotelRepository hotelRepository;


    @Autowired
    HotelImageRepository hotelImageRepository;

    @Test
    @WithMockUser(username = "owner", roles = "OWNER")
    @DisplayName("호텔 정보 뽑아보기")
    void test(){

        hotelAPIService.getData(HotelRequestData.builder()
                .key("26f037dc249943e4b75e005d6fc3858d")
                .type("json")
                .build()).getGSST().get(1).getRowList().forEach(row -> {
                    hotelRepository.save(Hotel.builder()
                            .hotelName(row.getENTRPS_NM())
                            .cityName(row.getSIGUN_NM())
                            .tellNumber(row.getTELNO())
                            .latitude(row.getREFINE_WGS84_LAT())
                            .longitude(row.getREFINE_WGS84_LOGT())
                            .build());
                });
        System.out.println();
        //https://openapi.gg.go.kr/GSST?key=26f037dc249943e4b75e005d6fc3858d
//        hotelRepository.save(Hotel.builder()
//                .hotelName(row.getENTRPS_NM())
//                .cityName(row.getSIGUN_NM())
//                .tellNumber(row.getTELNO())
//                .latitude(row.getREFINE_WGS84_LAT())
//                .longitude(row.getREFINE_WGS84_LOGT())
//                .build());
    }

    @Test
    @WithMockUser(username = "owner", roles = "OWNER")
    void save(){

        hotelRepository.save(Hotel.builder()
                        .cityName("123")
                        .latitude("37.7849662009")
                        .longitude("126.6820458015")
                        .hotelName("123")
                        .tellNumber("1111")
                        .writer("asdf")
                .build());
    }
    @Test
    @DisplayName("Hotel IMAGE 불러오기 위한 데이터 삽입")
    void HotelAPIServiceTest() {
        // given
        String uuid = UUID.randomUUID().toString();
        String imageName = "image";

        // when
        Hotel hotel = hotelRepository.findById(1L).get();

        // then
        hotelImageRepository.save((HotelImage.builder()
                .hotel(hotel)
                .name(uuid+"_"+imageName)
                .build()));
    }
>>>>>>> 87aed1abae7e6e0ecc7c0afd6d271ac83d60b074
}