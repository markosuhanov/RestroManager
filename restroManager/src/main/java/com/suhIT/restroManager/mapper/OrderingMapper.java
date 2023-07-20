package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.model.OrderedItem;
import com.suhIT.restroManager.model.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderingMapper implements Mapper<Ordering, OrderingDTO> {

    private final OrderedItemMapper orderedItemMapper;
    private final UserMapper userMapper;

    @Autowired
    public OrderingMapper(OrderedItemMapper orderedItemMapper, UserMapper userMapper) {
        this.orderedItemMapper = orderedItemMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Ordering toEntity(OrderingDTO orderingDTO) {
        return null;
    }

    @Override
    public OrderingDTO toDTO(Ordering ordering) {
        List<OrderedItem> orderedItems = ordering.getOrderedItems();
        List<OrderedItemDTO> orderedItemDTOList = new ArrayList<>();
        for (OrderedItem orditem : orderedItems) {
            orderedItemDTOList.add(orderedItemMapper.toDTO(orditem));
        }

        return OrderingDTO.builder()
                .id(ordering.getId())
                .tableName(ordering.getTable().getName())
                .orderedItemDTOS(orderedItemDTOList)
                .price(ordering.getPrice())
                .cost(ordering.getCost())
                .waiter(userMapper.toDTO(ordering.getWaiter()))
                .isPlaced(ordering.isPlaced())
                .build();
    }


}
