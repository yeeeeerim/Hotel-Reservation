package org.hotel.back.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hotel.back.domain.*;
import org.hotel.back.repository.custom.ManagerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ManagerRepositoryImpl implements ManagerRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @PersistenceContext
    private EntityManager em;

    /**
     * @apiNote 이메일을 받고 해당 계정이 작성한 호텔정보 - 즉 Hotel관리자가 볼 수 있는 정보이다.
     * 해당 사용자가 작성한 글이 없을 수 있으므로 null처리가 필요하다.
     *
     * */
    public Optional<List<Review>> getReviewInfo(String email){

        QReview review =  QReview.review;

        List<Review> hotelData = jpaQueryFactory.select(review)
                .from(review)
                .where(review.reviewWriter.eq(email))
                .fetch();

        if(hotelData != null)  return  Optional.of(hotelData);
        return Optional.empty();
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
