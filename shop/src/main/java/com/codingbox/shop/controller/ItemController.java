package com.codingbox.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.dto.ItemForm;
import com.codingbox.shop.service.ItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	@GetMapping("/items/new")
	public String createForm(Model model) {
		model.addAttribute("form", new ItemForm());
		return "items/createItemForm";
	}
	
	@PostMapping("/items/new")
	public String create(ItemForm form) {
		Item item = new Item();
		item.setName(form.getName());
		item.setPrice(form.getPrice());
		item.setStockQuantity(form.getStockQuantity());
		
		itemService.saveItem(item);
		return "redirect:/";
	}
	
	@GetMapping("/items")
	public String list(Model model) {
		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		return "items/itemList";
	}
	
	@GetMapping("/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId")Long itemId,
								Model model) {
		Item item = itemService.findOne(itemId);
		
		ItemForm form = new ItemForm();
		form.setId(itemId);
		form.setName(item.getName());
		form.setPrice(item.getPrice());
		form.setStockQuantity(item.getStockQuantity());
		model.addAttribute("form", form);
		return "items/updateItemForm";
	}
	
	/*
	@PostMapping("/items/{itemId}/edit")
	public String updateItemForm(@ModelAttribute("form")Item form) {
		Item item = new Item();
		item.setId(form.getId());
		// 변경을 원치않는 값의 경우 내가 set을 해주지 않으면
		// 해당 값이 null로 update가 된다.
		// 필요한 값만 update 원한다면 변경감지를 해주어야 한다.
		// 즉, merge는 실무에서 절대 쓰지 않는 사용법이라고 보면 된다.
//		item.setName(form.getName());
		item.setPrice(form.getPrice());
		item.setStockQuantity(form.getStockQuantity());

		itemService.saveItem(item);
		return "redirect:/items";
	}
	*/
	
	@PostMapping("/items/{itemId}/edit")
	public String updateItem(@PathVariable Long itemId,
				@ModelAttribute("form")Item form) {
//		Item item = new Item();
//		item.setId(form.getId());
//		item.setName(form.getName());
//		item.setPrice(form.getPrice());
//		item.setStockQuantity(form.getStockQuantity());
//		
//		itemService.updateItem(form.getId(), item);
	
		// 내가 필요한 값들만 명확하게 넣어준다. 유지보수에 좋다.
		itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
		
		return "redirect:/items";		
	}
	
}












