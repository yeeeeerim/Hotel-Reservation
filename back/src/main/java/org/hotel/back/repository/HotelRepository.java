package org.hotel.back.repository;

import org.hotel.back.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query("select distinct h from Hotel h left join fetch h.reviews where h.id = :id")
    Hotel findFetchJoin(@Param("id")Long id);
    //fetch join-> review테이블에서 한 번 더 쿼리가 일어나는 것을 방지
    //호텔 id에 저장되어있는 리뷰를 불러옴
}
