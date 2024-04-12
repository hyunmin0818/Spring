package com.codingbox.shop.domain;

import com.codingbox.shop.exception.NotEnoughStockException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {
	
	@Id @GeneratedValue
	@JoinColumn(name="item_id")
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;
	
//	========== 비즈니스 로직 ===============
	public void removeStock(int count) {
		int restStock = this.stockQuantity - count;
		
		// 재고 부족시 로직 처리
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
	
	// stock 증가
	public void addStock(int count) {
		this.stockQuantity += count;
	}

}
