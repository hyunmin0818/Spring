package com.codingbox.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemForm {
    @NotNull
    private Long id;

    @NotEmpty(message = "물품 이름은 필수값입니다.")
    private String name;

    private int price;
    private int stockQuantity;
}
