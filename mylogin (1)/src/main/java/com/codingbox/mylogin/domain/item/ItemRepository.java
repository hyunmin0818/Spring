package com.codingbox.mylogin.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    public Item save(Item item) {
        ++sequence;
        item.setId(sequence);
        store.put(item.getId(),item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<Item>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        // item을 먼저 찾음
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }

}
