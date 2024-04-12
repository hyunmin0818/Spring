package com.codingbox.shop.domain;

import com.codingbox.shop.exception.NotEnoughStockException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    // business logic
    public void removeStock(int count) {
        int restStock = this.stockQuantity-count;
        // 재고 부족 시 로직 처리
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
    // stock plus
    public void addStock(int count) {
        this.stockQuantity += count;
    }
}
