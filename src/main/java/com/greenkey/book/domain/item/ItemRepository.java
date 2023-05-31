package com.greenkey.book.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 2023.5.31(수) 0h20
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 2023.5.31(수) 0h55
    List<Item> findByName(String name);

    // 2023.5.31(수) 1h10
    List<Item> findByNameOrDescription(String name, String description); // where i1_0.name=? or i1_0.description=?

    // 2023.5.31(수) 18h
    List<Item> findByPriceLessThanEqual(int price); // where i1_0.price<=?

    // 2023.5.31(수) 18h10
    // Java 객체(o) db 컬럼(x) 기준 속성명 기재 -> No property 'itemId' found for type 'Item'
    List<Item> findByPriceGreaterThanOrderByIdDesc(int price); // where i1_0.price>? order by i1_0.item_id desc
}
