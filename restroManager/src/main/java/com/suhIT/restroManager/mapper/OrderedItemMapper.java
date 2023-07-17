package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.exception.OrderedItemNotFound;
import com.suhIT.restroManager.model.OrderedItem;
import com.suhIT.restroManager.repository.OrderRepository;
import com.suhIT.restroManager.repository.OrderedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class OrderedItemMapper implements Mapper<OrderedItem, OrderedItemDTO> {

    private final OrderedItemRepository orderedItemRepository;
    private final OrderRepository orderRepository;


    @Autowired
    public OrderedItemMapper(OrderedItemRepository orderedItemRepository, OrderRepository orderRepository) {
        this.orderedItemRepository = orderedItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderedItem toEntity(OrderedItemDTO orderedItemDTO) {
        OrderedItem orderedItem = orderedItemRepository.findById(orderedItemDTO.getId()).orElseThrow(
                () -> new OrderedItemNotFound(HttpStatus.NOT_FOUND,
                        "Ordered item with id + " + orderedItemDTO.getId() + " not found!"
                ));
        return null;

    }

    @Override
    public OrderedItemDTO toDTO(OrderedItem orderedItem) {
        return null;
    }
}
