package com.suhIT.restroManager.service;

import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.Order;
import com.suhIT.restroManager.model.OrderedItem;

import java.util.List;

public interface OrderedItemService {

    OrderedItem createOrderedItemFromItem(Item item);
    List<OrderedItem> createOrderedItemListFromItems(List<Item> items);


//    List<OrderedItem> getAllByOrder(Order order);

//    List<OrderedItemDTO> getDrinkOrderedItemsByOrderId(Long orderId);
}
