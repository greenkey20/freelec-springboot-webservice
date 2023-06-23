package com.greenkey.book.cart;

import com.greenkey.book.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

// 2023.6.4(일) 18h45
@Entity
@Getter
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private Member member;
}
