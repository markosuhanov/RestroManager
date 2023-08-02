package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.model.DinnerTable;
import com.suhIT.restroManager.model.OrderedItem;
import com.suhIT.restroManager.model.Ordering;
import com.suhIT.restroManager.repository.DinnerTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderingMapper implements Mapper<Ordering, OrderingDTO> {

    private final OrderedItemMapper orderedItemMapper;
    private final UserMapper userMapper;
    private final DinnerTableRepository tableRepository;
    @Autowired
    public OrderingMapper(OrderedItemMapper orderedItemMapper, UserMapper userMapper, DinnerTableRepository tableRepository) {
        this.orderedItemMapper = orderedItemMapper;
        this.userMapper = userMapper;
        this.tableRepository = tableRepository;

    }

    @Override
    public Ordering toEntity(OrderingDTO orderingDTO) {

        return Ordering.builder().id(orderingDTO.getId())
                .table(tableRepository.findByName(orderingDTO.getTableName()).orElse(null))
                .orderedItems(orderingDTO.getOrderedItemDTOS().stream().map(orderedItemMapper::toEntity).collect(
                        Collectors.toList()))
                .price(orderingDTO.getPrice())
                .cost(orderingDTO.getCost())
                .waiter(userMapper.toEntity(orderingDTO.getWaiter()))
                .isPlaced(orderingDTO.isPlaced())
                .build();

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
