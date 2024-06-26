package com.codingbox.mylogin.domain.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor	// 생성자를 자동으로 만들어 준다.
public class BasicItemContoller {
	
	private final ItemRepository itemRepository;
	
	@GetMapping
	public String items(Model model) {
		// 로그인 여부 체크
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}
	
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model ) {
		// 로그인 여부 체크
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	@GetMapping("/add")
	public String addForm() {
		// 로그인 여부 체크
		return "basic/addForm";
	}
	
	@PostMapping("/add")
	public String saveV5(Item item) {
		// 로그인 여부 체크
		itemRepository.save(item);
		return "redirect:/basic/items/" + item.getId();
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		// 로그인 여부 체크
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, 
				@ModelAttribute Item item) {
		// 로그인 여부 체크
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
	}
	
}









