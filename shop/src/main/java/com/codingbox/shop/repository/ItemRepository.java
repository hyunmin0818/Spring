package com.codingbox.shop.repository;

import com.codingbox.shop.domain.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @Autowired
    private final EntityManager em;

    public void save(Item item) {
//        if (item.getId() == null) { // 신규건
            em.persist(item);
//        } else {
//            //update
//            em.merge(item);
//        }
    }
    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }

}
