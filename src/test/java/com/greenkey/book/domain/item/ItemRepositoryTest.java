package com.greenkey.book.domain.item;

import com.greenkey.book.constant.ItemSellStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static com.greenkey.book.domain.item.QItem.item;
import static org.assertj.core.api.Assertions.assertThat;

// 2023.5.31(수) 0h30
@SpringBootTest // 통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션, 애플리케이션 실제 구동 시처럼 모든 Bean을 IoC 컨테이너에 등록 -> 애플리케이션 규모가 크면 속도 느려질 수 있음
@TestPropertySource(locations = "classpath:application-test.yml") // 테스트 코드 실행 시에는 h2 db 사용
class ItemRepositoryTest {
    private String name = "테스트 상품";
    private String description = "테스트 상품 상세 설명";
    private int price = 10000;
    private int stockNum = 100;
    private ItemSellStatus itemSellStatus = ItemSellStatus.SELL;

    @Autowired // 사용하기 위해 Bean 주입
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @AfterEach
    public void cleanUp() {
        itemRepository.deleteAll();
    }

    @Test // 해당 메서드를 테스트 대상으로 지정
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        // given
        itemRepository.save(Item.builder()
                .name(this.name)
                .price(this.price)
                .stockNum(this.stockNum)
                .description(this.description)
                .itemSellStatus(this.itemSellStatus)
                .build());

        // when
        List<Item> itemsList = itemRepository.findAll();

        // then
        Item item = itemsList.get(0);
        assertThat(item.getName()).isEqualTo(name);
        assertThat(item.getPrice()).isEqualTo(price);
    }

    // 2023.5.31(수) 1h15 테스트용 상품 10개 만드는 메서드
    public void createItemsList() {
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;

        for (int i = 1; i <= 10; i++) {
            itemRepository.save(Item.builder()
                    .name(this.name + i)
                    .price(this.price + i)
                    .stockNum(this.stockNum)
                    .description(this.description + i)
                    .itemSellStatus(this.itemSellStatus)
                    .build()); // Item(id=1, name=테스트 상품, price=10000, stockNum=100, description=테스트 상품 상세 설명, itemSellStatus=SELL)

            // itemRepository에 테스트 상품 하나만 저장되는 건 이 메서드의 내용 때문이 아니었음 -> 실수로 이 메서드를 호출하지 않고 createItemTest()를 호출했기 때문
            /*
            Item stubItem = Item.builder()
                    .name(this.name + i)
                    .price(price + i)
                    .stockNum(stockNum)
                    .description(description + i)
                    .itemSellStatus(itemSellStatus)
                    .build();

            itemRepository.save(stubItem);
             */
        }
    }

    // 2023.6.3(토) 23h45
    public void createItemsList2() {
        for (int i = 1; i <= 5; i++) {
            itemRepository.save(Item.builder()
                    .name(this.name + i)
                    .price(this.price + i)
                    .stockNum(this.stockNum)
                    .description(this.description + i)
                    .itemSellStatus(this.itemSellStatus)
                    .build());
        }

        for (int i = 6; i <= 10; i++) {
            itemRepository.save(Item.builder()
                    .name(this.name + i)
                    .price(this.price + i)
                    .stockNum(0)
                    .description(this.description + i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .build());
        }
    }

    // 2023.5.31(수) 0h55
    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByNameTest() {
        // given
        createItemsList();

        // 2023.5.31(수) 17h35 이 테스트가 의도하는대로 동작하지 않아서 확인
        /*
        List<Item> allItemsList = itemRepository.findAll();

        System.out.println("---- 상품명 조회 테스트 중 print ----");
        for (Item item : allItemsList) {
            System.out.println(item);
        }
         */

        // when
        List<Item> itemsList = itemRepository.findByName(this.name + 1);

        // then
        Item item = itemsList.get(0);
        assertThat(itemsList.size()).isEqualTo(1);
        assertThat(item.getName()).isEqualTo(this.name + 1);
    }

    // 2023.5.31(수) 17h50
    @Test
    @DisplayName("상품명 또는 상품 상세 설명 검색 테스트")
    public void findByNameOrDescriptionTest() {
        // given
        createItemsList();

        // when
        List<Item> searchResult1 = itemRepository.findByNameOrDescription(this.name + 1, this.description + 4); // case1)
        List<Item> searchResult2 = itemRepository.findByNameOrDescription(null, this.description + 4); // case2)

        // then
        assertThat(searchResult1.size()).isEqualTo(2); // case1)
        assertThat(searchResult2.size()).isEqualTo(1); // case2)
    }

    // 2023.5.31(수) 18h
    @Test
    @DisplayName("특정 가격 이하 상품 검색 테스트")
    public void findByPriceLessThanEqualTest() {
        // given
        createItemsList();

        // when
        List<Item> searchResult = itemRepository.findByPriceLessThanEqual(this.price + 4);

        // then
        assertThat(searchResult.size()).isEqualTo(4);
    }

    // 2023.5.31(수) 18h10
    @Test
    @DisplayName("특정 가격 초과 상품 검색 + 상품id 내림차순 정렬 테스트")
    public void findByPriceGreaterThanOrderByIdDescTest() {
        // given
        createItemsList(); // @AfterEach로 각 단위 테스트 후 db 저장 내용 지워도 pk는 계속 쌓인다?

        // when
        List<Item> searchResult = itemRepository.findByPriceGreaterThanOrderByIdDesc(this.price + 4);

        // then
        assertThat(searchResult.size()).isEqualTo(6);
//        assertThat(searchResult.get(0).getId()).isEqualTo(10); // 2023.6.2(금) 23h 이 클래스의 다른 테스트들과 같이 테스트 시 createItemsList()에서 id가 계속 쌓이는 바, 테스트 결과 예측 어려움
        assertThat(searchResult.get(0).getPrice()).isEqualTo(10010);
    }

    // 2023.5.31(수) 18h25
    @Test
    @DisplayName("상품 상세 설명 검색")
    public void findByDescriptionTest() {
        // given
        createItemsList();

        // when
        List<Item> searchResult = itemRepository.findByDescription(this.description);

        // then
        assertThat(searchResult.size()).isEqualTo(10);
        assertThat(searchResult.get(searchResult.size() - 1).getId()).isEqualTo(1);
    }

    // 2023.6.2(금) 23h5
    @Test
    @DisplayName("상품 Querydsl 조회 테스트1")
    public void queryDslTest1() {
        // given
        createItemsList();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        QItem qItem = QItem.item;

        // when
        List<Item> searchResult = queryFactory.selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(item.description.like("%" + "설명" + "%"))
                .orderBy(item.price.desc())
                .fetch();

        // then
        assertThat(searchResult.size()).isEqualTo(10);
        assertThat(searchResult.get(0).getPrice()).isEqualTo(10010);
    }

    // 2023.6.3(토) 23h50
    @Test
    @DisplayName("상품 Querydsl 조회 테스트2")
    public void queryDslTest2() {
        // given
        createItemsList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // 쿼리에 들어갈 조건을 만들어주는 빌더
        String searchDescription = "설명";
        int searchPrice = 10003;
        String searchItemSellStatus = "SELL";

        booleanBuilder.and(item.description.like("%" + searchDescription + "%"));
        booleanBuilder.and(item.price.gt(searchPrice));

        // 잘 이해 안가는 조건

        // when
        Pageable pageable = PageRequest.of(0, 5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);

        List<Item> resultItemList = itemPagingResult.getContent();

        // then
        System.out.println("total elements = " + itemPagingResult.getTotalElements());

        for (Item resultItem : resultItemList) {
            System.out.println(resultItem.toString());
        }
    }
}