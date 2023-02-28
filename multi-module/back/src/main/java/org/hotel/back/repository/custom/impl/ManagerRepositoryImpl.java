package org.hotel.back.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hotel.back.domain.*;
import org.hotel.back.repository.custom.ManagerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManagerRepositoryImpl implements ManagerRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @PersistenceContext
    private EntityManager em;

    /**
     * @apiNote 이메일을 받고 해당 이메일과 조회하려는 호텔 정보중 작성자와 동일하다면 데이터 반환
     *
     * */
    public Hotel getHotelInfo(String email){
        QHotel hotel = QHotel.hotel;
        QReview review =  QReview.review;
        QRoom room = QRoom.room;
        QHotelImage hotelImage = QHotelImage.hotelImage;

        Hotel hotelData = jpaQueryFactory.select(hotel)
                .distinct()
                .from(hotel)
                .join(hotel.reviews, review)
                .fetchJoin()
                .join(hotel.roomSet, room)
                .fetchJoin()
                .fetchOne();

        if(hotelData.getWriter().equals(email)){
            return hotelData;
        }
        return hotelData;
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





}
