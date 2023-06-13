package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long id, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(id);
        // 준영속성으로 영속성컨텍스트에 저장안되어있음.
        log.info("findItem >>>>>> " + "name : " + name + "price : " + price + "stockQuantity : " + stockQuantity );
        // 영속성컨텍스트에 저장되어있는 속성
        log.info("findItem >>>>>> " + "name : " + findItem.getName() + "price : " + findItem.getPrice() + "stockQuantity : " + findItem.getStockQuantity() );
        findItem.change(name, price, stockQuantity);
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

}
