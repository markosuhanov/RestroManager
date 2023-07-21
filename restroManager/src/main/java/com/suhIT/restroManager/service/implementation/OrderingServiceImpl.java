package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.exception.NotAllItemsPreparedException;
import com.suhIT.restroManager.exception.OrderingNotFoundException;
import com.suhIT.restroManager.exception.TableNotFoundException;
import com.suhIT.restroManager.mapper.OrderedItemMapper;
import com.suhIT.restroManager.mapper.OrderingMapper;
import com.suhIT.restroManager.mapper.UserMapper;
import com.suhIT.restroManager.model.*;
import com.suhIT.restroManager.repository.DinnerTableRepository;
import com.suhIT.restroManager.repository.OrderedItemRepository;
import com.suhIT.restroManager.repository.OrderingRepository;
import com.suhIT.restroManager.service.OrderedItemService;
import com.suhIT.restroManager.service.OrderingService;
import com.suhIT.restroManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderingServiceImpl implements OrderingService {

    private final DinnerTableRepository dinnerTableRepository;
    private final OrderedItemService orderedItemService;
    private final UserMapper userMapper;
    private final UserService userService;
    private final OrderingMapper orderingMapper;
    private final OrderingRepository orderingRepository;
    private final OrderedItemMapper orderedItemMapper;
    private final OrderedItemRepository orderedItemRepository;

    @Autowired
    public OrderingServiceImpl(DinnerTableRepository dinnerTableRepository,
                               OrderedItemService orderedItemService, UserService userService, UserMapper userMapper, OrderedItemMapper orderedItemMapper, OrderingMapper orderingMapper,OrderingRepository orderingRepository,
                               OrderedItemRepository orderedItemRepository) {
        this.dinnerTableRepository = dinnerTableRepository;
        this.orderedItemService = orderedItemService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.orderedItemMapper = orderedItemMapper;
        this.orderingMapper = orderingMapper;
        this.orderingRepository = orderingRepository;
        this.orderedItemRepository = orderedItemRepository;
    }

    @Override
    public OrderingDTO createOrder(OrderingDTO orderDto) {

        DinnerTable table = dinnerTableRepository.findByName(orderDto.getTableName()).orElseThrow(
                () -> new TableNotFoundException(HttpStatus.NOT_FOUND, "Table with name " + orderDto.getTableName() + " not found!"));


        List<OrderedItemDTO> orderedItemDTOS = orderDto.getOrderedItemDTOS();
        List<OrderedItem> orderedItems = orderedItemDTOS.stream().map(orderedItemMapper::toEntity)
                .collect(Collectors.toList());

        double totalPrice = orderedItems.stream()
                .mapToDouble(orderedItem -> orderedItem.getItem().getPrice())
                .sum();

        double totalCost = orderedItems.stream()
                .mapToDouble(orderedItem -> orderedItem.getItem().getCost())
                .sum();
        User waiter = userMapper.toEntity(userService.getLoggedUser());
        boolean isPlaced = false;
        Ordering newOrdering = Ordering.builder()
                .table(table)
                .waiter(waiter)
                .orderedItems(orderedItems)
                .price(totalPrice)
                .cost(totalCost)
                .isPlaced(isPlaced)
                .build();

        return orderingMapper.toDTO(orderingRepository.save(newOrdering));
    }

    @Override
    public OrderingDTO getById(Long id) {
        Ordering ordering =  orderingRepository.findById(id).orElseThrow(
                () -> new OrderingNotFoundException(HttpStatus.NOT_FOUND, "Ordering with id " + id + " not found!"));
        return orderingMapper.toDTO(ordering);

    }

    @Override
    public Ordering getEntityById(Long id) {
        Ordering ordering =  orderingRepository.findById(id).orElseThrow(
                () -> new OrderingNotFoundException(HttpStatus.NOT_FOUND, "Ordering with id " + id + " not found!"));
        return ordering;
    }

    public void areAllItemsPrepared(List<OrderedItemDTO> orderedItemDTOS) {
        for (OrderedItemDTO dto: orderedItemDTOS) {
            if (!dto.isPrepared()) {
                throw new NotAllItemsPreparedException(HttpStatus.BAD_REQUEST,
                        "Item with name " + dto.getItem().getName() + " is not prepared!"
                );
            }
        }
    }

    @Override
    public List<OrderedItemDTO> getAllDrinksFromOrderId(Long orderId) {
        Ordering ordering = getEntityById(orderId);
        List<OrderedItem> orderedDrinks = ordering.getOrderedItems().stream().filter(orderedItem -> orderedItem.getItem() instanceof DrinkItem)
                .collect(Collectors.toList());
        return orderedDrinks.stream().map(orderedItemMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public List<OrderedItemDTO> getAllFoodsFromOrderId(Long orderId) {
        Ordering ordering = getEntityById(orderId);
        List<OrderedItem> orderedFood = ordering.getOrderedItems().stream().filter(orderedItem -> orderedItem.getItem() instanceof FoodItem)
                .collect(Collectors.toList());
        return orderedFood.stream().map(orderedItemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        orderingRepository.deleteById(id);
    }

    @Override
    public OrderedItemDTO itemMade(Long orderedItemId) {
        OrderedItem orderedItem = orderedItemService.getById(orderedItemId);
        orderedItem.setPrepared(true);
        return orderedItemMapper.toDTO(orderedItemRepository.save(orderedItem));
    }

    @Override
    public List<OrderingDTO> getAll() {
        List<Ordering> orderings = orderingRepository.findAll();
        return orderings.stream().map(orderingMapper::toDTO).collect(Collectors.toList());
    }

}
