package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.exception.ItemNotFoundException;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.OrderedItem;
import com.suhIT.restroManager.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component

public class OrderedItemMapper implements Mapper<OrderedItem, OrderedItemDTO> {

    private final ItemMapper itemMapper;

    private final ItemRepository itemRepository;

    @Autowired
    public OrderedItemMapper( ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public OrderedItem toEntity(OrderedItemDTO orderedItemDTO) {
        Item item = itemRepository.findByName(orderedItemDTO.getItem().getName()).orElseThrow(
                () -> new ItemNotFoundException(HttpStatus.NOT_FOUND,
                        "Item with name + " + orderedItemDTO.getItem().getName() + " not found!"
                ));
        OrderedItem orderedItem = OrderedItem.builder()
                .item(item)
                .active(orderedItemDTO.isActive())
                .prepared(orderedItemDTO.isPrepared())
                .build();
        return orderedItem;

    }

    @Override
    public OrderedItemDTO toDTO(OrderedItem orderedItem) {
        return OrderedItemDTO.builder()
                .item(itemMapper.toDTO(orderedItem.getItem()))
                .id(orderedItem.getId())
                .prepared(orderedItem.isPrepared())
                .build();
    }


}
