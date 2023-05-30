package com.greenkey.book.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 2023.5.31(수) 0h20
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 2023.5.31(수) 0h55
    List<Item> findByName(String name);

    // 2023.5.31(수) 1h10
    List<Item> findByNameOrDescription(String name, String description);
}
