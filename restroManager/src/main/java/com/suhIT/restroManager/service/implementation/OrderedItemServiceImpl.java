package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.OrderedItem;
import com.suhIT.restroManager.repository.OrderedItemRepository;
import com.suhIT.restroManager.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;

    @Autowired
    public OrderedItemServiceImpl(OrderedItemRepository orderedItemRepository) {
        this.orderedItemRepository = orderedItemRepository;
    }

    @Override
    public OrderedItem createOrderedItemFromItem(Item item) {
        return OrderedItem.builder().item(item).prepared(false).active(true).build();
    }

    @Override
    public void deleteById(Long id) {
        orderedItemRepository.deleteById(id);
    }


}
