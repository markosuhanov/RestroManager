package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.exception.OrderedItemNotFound;
import com.suhIT.restroManager.mapper.OrderedItemMapper;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.OrderedItem;
import com.suhIT.restroManager.repository.OrderedItemRepository;
import com.suhIT.restroManager.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;
    private final OrderedItemMapper orderedItemMapper;

    @Autowired
    public OrderedItemServiceImpl(OrderedItemRepository orderedItemRepository, OrderedItemMapper orderedItemMapper) {
        this.orderedItemRepository = orderedItemRepository;
        this.orderedItemMapper = orderedItemMapper;
    }

    @Override
    public OrderedItem createOrderedItemFromItem(Item item) {
        return OrderedItem.builder().item(item).prepared(false).active(true).build();
    }

    @Override
    public void deleteById(Long id) {
        orderedItemRepository.deleteById(id);
    }

    @Override
    public OrderedItem getById(Long id) {
        return orderedItemRepository.findById(id).orElseThrow(
                () -> new OrderedItemNotFound(HttpStatus.NOT_FOUND, "Ordered item with id + " + id + " not found!"));
    }

    @Override
    public OrderedItemDTO orderedItemPrepared(Long orderedItemId) {
        OrderedItem orderedItem = orderedItemRepository.findById(orderedItemId).orElseThrow(
                () -> new OrderedItemNotFound(HttpStatus.NOT_FOUND,
                        "Ordered item with id " + orderedItemId + " not found!"
                ));
        orderedItem.setPrepared(true);
        return orderedItemMapper.toDTO(orderedItemRepository.save(orderedItem));
    }


}
