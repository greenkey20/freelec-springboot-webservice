package com.greenkey.book.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 2023.5.31(수) 0h20
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    // 2023.5.31(수) 0h55
    List<Item> findByName(String name);

    // 2023.5.31(수) 1h10
    List<Item> findByNameOrDescription(String name, String description); // where i1_0.name=? or i1_0.description=?

    // 2023.5.31(수) 18h
    List<Item> findByPriceLessThanEqual(int price); // where i1_0.price<=?

    // 2023.5.31(수) 18h10
    // Java 객체/entity(o) db 컬럼(x) 기준 속성명 기재 -> No property 'itemId' found for type 'Item'
    List<Item> findByPriceGreaterThanOrderByIdDesc(int price); // where i1_0.price>? order by i1_0.item_id desc

    // 2023.5.31(수) 18h20
    // JPQL, Java Persistence Query Language = entity 대상으로 쿼리 수행 + sql을 추상화, 특정 db에 의존x
    // 또는 native query(특정 db에 의존) 활용
//    @Query("select i from Item i where i.description like %:description% order by i.price desc")
    @Query(value = "select * from item i where i.description like %:description% order by i.price desc", nativeQuery = true)
    List<Item> findByDescription(@Param("description") String description);

    // 복잡한 native query(e.g. JOIN 등) 적용 시 추가 기능 ref = https://hackids.tistory.com/129
}
