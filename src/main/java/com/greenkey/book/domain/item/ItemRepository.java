package com.greenkey.book.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

// 2023.5.31(수) 0h20
public interface ItemRepository extends JpaRepository<Item, Long> {
}
