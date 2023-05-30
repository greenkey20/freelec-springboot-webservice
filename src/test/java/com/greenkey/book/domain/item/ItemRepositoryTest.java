package com.greenkey.book.domain.item;

import com.greenkey.book.constant.ItemSellStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 2023.5.31(수) 0h30
@SpringBootTest // 통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션, 애플리케이션 실제 구동 시처럼 모든 Bean을 IoC 컨테이너에 등록 -> 애플리케이션 규모가 크면 속도 느려질 수 있음
@TestPropertySource(locations = "classpath:application-test.yml") // 테스트 코드 실행 시에는 h2 db 사용
class ItemRepositoryTest {
    @Autowired // 사용하기 위해 Bean 주입
    ItemRepository itemRepository;

    @AfterEach
    public void cleanUp() {
        itemRepository.deleteAll();
    }

    @Test // 해당 메서드를 테스트 대상으로 지정
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        // given
        String name = "테스트 상품";
        int price = 10000;
        int stockNum = 100;
        String description = "테스트 상품 상세 설명";
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;

        itemRepository.save(Item.builder()
                .name(name)
                .price(price)
                .stockNum(stockNum)
                .description(description)
                .itemSellStatus(itemSellStatus)
                .build());

        // when
        List<Item> itemsList = itemRepository.findAll();

        // then
        Item item = itemsList.get(0);
        assertThat(item.getName()).isEqualTo(name);
        assertThat(item.getPrice()).isEqualTo(price);
    }

    // 2023.5.31(수) 0h55
    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByNameTest() {
        // given
        String name = "테스트 상품";
        int price = 10000;
        int stockNum = 100;
        String description = "테스트 상품 상세 설명";
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;

        for (int i = 1; i <= 10; i++) {
            itemRepository.save(Item.builder()
                    .name(name + i)
                    .price(price)
                    .stockNum(stockNum)
                    .description(description + i)
                    .itemSellStatus(itemSellStatus)
                    .build());
        }

        // when
        List<Item> itemsList = itemRepository.findByName(name + 1);

        assertThat(itemsList.size()).isEqualTo(1);
        assertThat(itemsList.get(0).getName()).isEqualTo(name + 1);
    }
}