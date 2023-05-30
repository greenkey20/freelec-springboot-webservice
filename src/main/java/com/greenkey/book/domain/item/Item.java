package com.greenkey.book.domain.item;

import com.greenkey.book.constant.ItemSellStatus;
import com.greenkey.book.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 2023.5.30(화) 23h50
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "item")
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id; // 상품 코드

    @Column(nullable = false)
    private String name; // 상품명

    @Column(nullable = false)
    private int price; // 상품 가격

    @Column(nullable = false)
    private int stockNum; // 재고 수량

    @Lob
    @Column(nullable = false)
    private String description; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    @Builder
    public Item(String name, int price, int stockNum, String description, ItemSellStatus itemSellStatus) {
        this.name = name;
        this.price = price;
        this.stockNum = stockNum;
        this.description = description;
        this.itemSellStatus = itemSellStatus;
    }
}
