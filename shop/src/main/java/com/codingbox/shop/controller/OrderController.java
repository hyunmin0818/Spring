package com.codingbox.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.domain.Member;
import com.codingbox.shop.domain.Order;
import com.codingbox.shop.dto.OrderSearch;
import com.codingbox.shop.service.ItemService;
import com.codingbox.shop.service.MemberService;
import com.codingbox.shop.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class OrderController {
	
	private final MemberService memberService;
	private final ItemService itemService;
	private final OrderService orderService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> itemes = itemService.findItems();
		
		model.addAttribute("members", members);
		model.addAttribute("items", itemes);
		
		return "order/orderForm";
	}
	
	@PostMapping("/order")
	public String order(@RequestParam("memberId") Long memberId,
						@RequestParam("itemId") Long itemId,
						@RequestParam("count") int count) {
		
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}
	
	// 주문 상세
	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch,
						Model model) {
		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);
		return "order/orderList";
	}
	
	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId")Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
	
	
}









