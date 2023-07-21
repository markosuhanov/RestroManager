package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.model.Ordering;

import java.util.List;

public interface OrderingService {

    OrderingDTO createOrder(OrderingDTO orderDto);
    OrderingDTO getById(Long id);

    Ordering getEntityById(Long id);

    List<OrderedItemDTO> getAllFoodsFromOrderId(Long orderId);
    List<OrderedItemDTO> getAllDrinksFromOrderId(Long orderId);

    void deleteById(Long id);

    OrderedItemDTO itemMade(Long orderedItemId);

    List<OrderingDTO> getAll();
}
