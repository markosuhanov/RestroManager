package com.suhIT.restroManager.service;

import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.OrderedItem;

import java.util.List;

public interface OrderedItemService {

    OrderedItem createOrderedItemFromItem(Item item);


}
