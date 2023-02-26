package org.hotel.back.repository.custom.impl;

import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hotel.back.annotation.PerformTimer;
import org.hotel.back.data.response.HotelResponseDTO;
import org.hotel.back.domain.*;
import org.hotel.back.repository.custom.ManagerRepository;
import org.junit.Before;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManagerRepositoryImpl implements ManagerRepository{
    QHotel hotel = QHotel.hotel;
    QReview review =  QReview.review;
    QRoom room = QRoom.room;
    QHotelImage hotelImage = QHotelImage.hotelImage;

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Before
    void beforeDataInsert(){

    }


    /**
     * @apiNote 이메일을 받고 해당 이메일과 조회하려는 호텔 정보중 작성자와 동일하다면 데이터 반환
     *
     * */
    @PerformTimer
    public Hotel getHotelInfo(String email){


        Hotel hotelData = jpaQueryFactory.select(hotel)
                .distinct()
                .from(hotel)
                .innerJoin(hotel.reviews, review)
                .fetchJoin()
//                .innerJoin(hotel.roomSet,room)
//                .fetchJoin()
                .fetchOne();

        if(hotelData.getWriter().equals(email)){
            return hotelData;
        }
        return hotelData;
    }

    @PerformTimer
    public List<Room> findByRoom(Long id){
        List<Room> roomData = jpaQueryFactory.selectFrom(room)
                    .where(room.hotelId.eq(2L))
                    .fetch();
        return roomData;
    }

    @Override
    public Hotel getJPQLHotelInfo(String email) {

        Hotel findByJPQL = em.createQuery("select h from Hotel h " +
                        "join fetch h.reviews " +
                        "join fetch h.roomSet " +
                        "where h.writer = :email",Hotel.class)
                .setParameter("email",email)
                .getSingleResult();
        return findByJPQL;
    }


    public HotelResponseDTO findByEmail(String email){

        Hotel hotelEntity = jpaQueryFactory.selectDistinct(hotel)
                    .join(hotelImage)
                    .fetchJoin()
                    .where(hotel.writer.eq(email))
                    .fetchOne();

        if(hotelEntity != null)  return new HotelResponseDTO(hotelEntity);

        return null;
    }





}
