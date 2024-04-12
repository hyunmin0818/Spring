package com.codingbox.shop.controller;

import java.util.List;

import com.codingbox.shop.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.dto.ItemForm;
import com.codingbox.shop.service.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm());
        return "items/createItemForm";
    }

    // 상품 저장
    @PostMapping("/items/new")
    public String saveItem(@Valid ItemForm form, BindingResult result) {
        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());

        itemService.saveItem(item);

        return "redirect:/";
    }

    // 상품 조회
    @GetMapping("/items")
    public String itemList(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        ItemForm form = new ItemForm();
        form.setId(itemId);
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    // 수정
//    @PostMapping("/items/{itemId}/edit")
//    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") @Valid ItemForm form, BindingResult result) {
//        if (result.hasErrors()) {
//            return "items/updateItemForm";
//        }
//
//        Item item = itemService.findOne(itemId);
//        // 변경을 원치않는 값의 경우 내가 set을 해주지 않으면 해당 값이 null로 update가 된다.
//        // 즉 merge는 실무에서 절대 쓰지 않는 사용법이라고 보면 된다.
//        // item.setName(form.getName());
//        item.setPrice(form.getPrice());
//        item.setStockQuantity(form.getStockQuantity());
//
//        itemService.saveItem(item);
//
//        return "redirect:/items";
//    }

    @PostMapping("/items/{itemId}/edit")
public String updateItem(@PathVariable Long itemId, @ModelAttribute("form")Item form){
//        Item item = new Item();
//        item.setId(form.getId());
//        item.setName(form.getName());
//        item.setPrice(form.getPrice());
//        item.setStockQuantity(form.getStockQuantity());
//
//        itemService.updateItem(form.getId(), item);
        // 내가 필요한 값들만 명확하게 넣어준다. 유지보수에 좋다.]

        itemService.updateItem(itemId,form.getName(),form.getPrice(),form.getStockQuantity());
        return "redirect:/items";
    }



}