package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.exception.NotAllItemsPreparedException;
import com.suhIT.restroManager.exception.OrderedItemNotFound;
import com.suhIT.restroManager.exception.OrderingNotFoundException;
import com.suhIT.restroManager.exception.TableNotFoundException;
import com.suhIT.restroManager.mapper.OrderedItemMapper;
import com.suhIT.restroManager.mapper.OrderingMapper;
import com.suhIT.restroManager.mapper.UserMapper;
import com.suhIT.restroManager.model.*;
import com.suhIT.restroManager.repository.DinnerTableRepository;
import com.suhIT.restroManager.repository.OrderedItemRepository;
import com.suhIT.restroManager.repository.OrderingRepository;
import com.suhIT.restroManager.service.DinnerTableService;
import com.suhIT.restroManager.service.OrderedItemService;
import com.suhIT.restroManager.service.OrderingService;
import com.suhIT.restroManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderingServiceImpl implements OrderingService {

    private final DinnerTableRepository dinnerTableRepository;
    private final DinnerTableService dinnerTableService;
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
                               OrderedItemRepository orderedItemRepository, DinnerTableService dinnerTableService) {
        this.dinnerTableRepository = dinnerTableRepository;
        this.dinnerTableService = dinnerTableService;
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
//        User waiter = userMapper.toEntity(userService.getLoggedUser());
        User waiter = this.userMapper.toEntity(this.userService.getUserByUsername(orderDto.getWaiter().getUsername()));

        boolean isPlaced = false;
        Ordering newOrdering = Ordering.builder()
                .table(table)
                .waiter(waiter)
                .cook(null)
                .bartender(null)
                .orderedItems(orderedItems)
                .price(totalPrice)
                .cost(totalCost)
                .isPlaced(isPlaced)
                .build();
        this.dinnerTableService.setAsBusy(table.getName());
        this.dinnerTableRepository.save(table);
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

    @Override
    public OrderingDTO updateOrder(OrderingDTO orderingDTO) {

        Ordering ordering = this.orderingRepository.findByTable_Name(orderingDTO.getTableName()).orElse(null);
        ordering.setWaiter(this.userMapper.toEntity(orderingDTO.getWaiter()));


        // Convert the new orderedItems from DTO to entities
        List<OrderedItem> newOrderedItems = orderingDTO.getOrderedItemDTOS()
                .stream()
                .map(this.orderedItemMapper::toEntity)
                .collect(Collectors.toList());

        // Remove the items that are no longer present in the updated orderingDTO
        List<OrderedItem> itemsToRemove = new ArrayList<>();
        for (OrderedItem existingItem : ordering.getOrderedItems()) {
            if (!newOrderedItems.contains(existingItem)) {
                itemsToRemove.add(existingItem);
            }
        }

        // Remove the identified items from the database
        for (OrderedItem itemToRemove : itemsToRemove) {
            itemToRemove.setOrdering(null);
            ordering.getOrderedItems().remove(itemToRemove);
            this.orderedItemRepository.delete(itemToRemove);
        }

        ordering.setOrderedItems(newOrderedItems);
        ordering.setPrice(newOrderedItems.stream().mapToDouble(orderedItem -> orderedItem.getItem().getPrice()).sum());
        ordering.setCost(newOrderedItems.stream().mapToDouble(orderedItem -> orderedItem.getItem().getCost()).sum());
        ordering.setTable(this.dinnerTableRepository.findByName(ordering.getTable().getName()).get());

        boolean arePrepared =  ordering.getOrderedItems().stream().allMatch(OrderedItem::isPrepared);
        ordering.setPlaced(arePrepared);
        return this.orderingMapper.toDTO(this.orderingRepository.save(ordering));
    }

    public void changingIsPlaced(Long orderingId) {
        Ordering ordering = this.orderingRepository.findById(orderingId).orElse(null);
        if (ordering != null) {
            boolean arePrepared =  ordering.getOrderedItems().stream().allMatch(OrderedItem::isPrepared);
            if (arePrepared) {
                ordering.setPlaced(true);
            }else{
                ordering.setPlaced(false);
            }
            this.orderingRepository.save(ordering);

        }
    }

    @Override
    public OrderingDTO getByTableName(String tableName) {
//        Ordering ordering = this.orderingRepository.findByTable_Name(tableName).orElseThrow(
//                () -> new OrderingNotFoundException(HttpStatus.NOT_FOUND,
//                        "Ordering where table name is " + tableName + " not found!"
//                ));

        Ordering ordering = this.orderingRepository.findByTable_Name(tableName).orElse(null);
        if(ordering == null){
            return null;
        }
        return orderingMapper.toDTO(ordering);
    }

    @Override
    public OrderingDTO takeOrder(Long orderingId) {
        Ordering ordering = this.orderingRepository.findById(orderingId).orElseThrow(
                () -> new OrderingNotFoundException(HttpStatus.NOT_FOUND,
                        "Ordering where id is " + orderingId + " not found!"
                ));
        UserDTO user = userService.getLoggedUser();
        if (user.getRole().equals(Role.BARTENDER)) {
            ordering.setBartender(userMapper.toEntity(user));
        } else if (user.getRole().equals(Role.COOK)) {
            ordering.setCook(userMapper.toEntity(user));
        }
        return orderingMapper.toDTO(orderingRepository.save(ordering));
    }

    @Override
    public OrderedItemDTO orderedItemPrepared(Long orderedItemId) {
        OrderedItem orderedItem = orderedItemRepository.findById(orderedItemId).orElseThrow(
                () -> new OrderedItemNotFound(HttpStatus.NOT_FOUND,
                        "Ordered item with id " + orderedItemId + " not found!"
                ));
        orderedItem.setPrepared(true);
        OrderedItem preparedOrderedItem = orderedItemRepository.save(orderedItem);
        changingIsPlaced(preparedOrderedItem.getOrdering().getId());
        return orderedItemMapper.toDTO(orderedItemRepository.save(preparedOrderedItem));
    }

    @Override
    public boolean isOrderPlaced(Long orderingId) {
        Ordering ordering = this.orderingRepository.findById(orderingId).orElse(null);
        if (ordering != null) {
            return ordering.isPlaced();
        }
        return false;
    }


}
