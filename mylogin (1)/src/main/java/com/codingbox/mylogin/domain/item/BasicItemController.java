package com.codingbox.mylogin.domain.item;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // lombok 라이브러리에서 생성자를 자동으로 만들어줌
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }
    @GetMapping("/{itemid}")
    public String item(@PathVariable Long itemid,Model model) {
        Item item = itemRepository.findById(itemid);
        model.addAttribute("item",item);
        return "basic/item";
    }

    // @getmapping
    // url : {itemid}/edit
    // 화면 : return basic/editForm
    @GetMapping("/{itemid}/edit")
    public String edit(@PathVariable("itemid") Long itemid,Model model) {
        Item item = itemRepository.findById(itemid);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "basic/addForm";
    }
    @PostMapping("/{itemid}/edit")
    public String update(@PathVariable("itemid") Long itemid,@ModelAttribute("item") Item item) {
        itemRepository.update(itemid,item);
        return "redirect:/basic/items/{itemid}";
    }
//    @PostMapping("/add")
    public String saveV1(@RequestParam String itemName,@RequestParam int price,
                       @RequestParam int quantity,Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item",item);
        return "basic/item";
    }
//    @PostMapping("/add")
    public String saveV2(@ModelAttribute("item") Item item) {
//        @ModelAttribute 가 해주는 역할
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//        model.addAttribute("item",item);
        itemRepository.save(item);
        return "basic/item";
    }
    /*
    @ModelAttribute name 생략 가능
    model.addAttribyte(item); 자동 추가, 생략 가능
    생략시 model에 저장되는 name은 클래스명 첫 글자만 소문자로 등록
    즉, Item -> item
    * */
//    @PostMapping("/add")
    public String saveV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }
//    @PostMapping("/add")
//    public String saveV3(@ModelAttribute HelloData item,Model model) {
//        itemRepository.save(item);
//        model.addAttribute("helloData",item);
//        return "basic/item";
//    }
    /*
    @ModelAttribute 자체도 생략 가능
    model.addAttribute("item",item); 자동 추가
    가독성을 위해 적당이 줄이는 것을 권장
    * */
//    @PostMapping("/add")
    public String saveV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }
    @PostMapping("/add")
    public String saveV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    // 테스트용 데이터 추가
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA",10000,10));
        itemRepository.save(new Item("testB",20000,20));
    }

    @PostConstruct
    public void init2() {
    }

    @PreDestroy
    public void destory() {
    }
}
