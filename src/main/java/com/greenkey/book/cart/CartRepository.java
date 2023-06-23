package com.greenkey.book.cart;

import org.springframework.data.jpa.repository.JpaRepository;

// 2023.6.4(일) 18h50
public interface CartRepository extends JpaRepository<Cart, Long> {
}
