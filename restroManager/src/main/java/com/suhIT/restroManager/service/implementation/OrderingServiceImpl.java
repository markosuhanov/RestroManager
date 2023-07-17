package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.exception.TableNotFoundException;
import com.suhIT.restroManager.exception.UserNotFoundException;
import com.suhIT.restroManager.model.*;
import com.suhIT.restroManager.repository.DinnerTableRepository;
import com.suhIT.restroManager.repository.UserRepository;
import com.suhIT.restroManager.service.OrderedItemService;
import com.suhIT.restroManager.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderingServiceImpl implements OrderingService {

    private final DinnerTableRepository dinnerTableRepository;
    private final UserRepository userRepository;
    private final OrderedItemService orderedItemService;

    @Autowired
    public OrderingServiceImpl(DinnerTableRepository dinnerTableRepository, UserRepository userRepository,
                               OrderedItemService orderedItemService) {
        this.dinnerTableRepository = dinnerTableRepository;
        this.userRepository = userRepository;
        this.orderedItemService = orderedItemService;
    }

    @Override
    public Ordering createOrder(OrderingDTO orderDto) {

        DinnerTable table = dinnerTableRepository.findByName(orderDto.getTableName()).orElseThrow(
                () -> new TableNotFoundException(HttpStatus.NOT_FOUND, "Table with name " + orderDto.getTableName() + " not found!"));

        User waiter = userRepository.findByUsername(orderDto.getWaiterUsername()).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND,
                        "Waiter with username " + orderDto.getWaiterUsername() + " not found!"
                ));
        List<OrderedItemDTO> orderedItemDTOS = orderDto.getOrderedItemDTOS();

//        List<OrderedItem> orderedItems = items.stream().map(orderedItemService::createOrderedItemFromItem)
//                .collect(Collectors.toList());
//
//        double totalPrice = items.stream().mapToDouble(Item::getPrice).sum();
//
//        double totalCost = items.stream().mapToDouble(Item::getCost).sum();
//
//        Ordering newOrder = Ordering.builder().table(table).waiter(waiter).orderedItems(orderedItems).price(totalPrice)
//                .cost(totalCost).isPlaced(false).build();
//        return newOrder;
        return null;
    }
}
