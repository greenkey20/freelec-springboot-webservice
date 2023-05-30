package com.greenkey.book.domain.item;

import com.greenkey.book.constant.ItemSellStatus;
import com.greenkey.book.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 2023.5.30(화) 23h50
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stockNum;
    private String description;
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    @Builder
    public Item(String name, int price, int stockNum, String description, ItemSellStatus itemSellStatus) {

    }
}
