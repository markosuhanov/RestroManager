package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.exception.OrderedItemsByOrderNotFound;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.Order;
import com.suhIT.restroManager.model.OrderedItem;
import com.suhIT.restroManager.repository.OrderedItemRepository;
import com.suhIT.restroManager.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return OrderedItem.builder().item(item).prepared(false).build();
    }

    @Override
    public List<OrderedItem> createOrderedItemListFromItems(List<Item> items) {
        return items.stream().map(item -> createOrderedItemFromItem(item)).collect(Collectors.toList());
    }

//    @Override
//    public List<OrderedItem> getAllByOrder(Order order) {
//        return orderedItemRepository.findAllByOrder(order).orElseThrow(
//                () -> new OrderedItemsByOrderNotFound(HttpStatus.NOT_FOUND,
//                        "There are no ordered items in order with id " + order.getId() + " !"
//                ));
//
//    }


}
