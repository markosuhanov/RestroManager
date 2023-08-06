package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.BillDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.exception.ItemNotFoundException;
import com.suhIT.restroManager.exception.NotAllItemsPreparedException;
import com.suhIT.restroManager.mapper.BillMapper;
import com.suhIT.restroManager.mapper.UserMapper;
import com.suhIT.restroManager.model.Bill;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.User;
import com.suhIT.restroManager.repository.BillRepository;
import com.suhIT.restroManager.repository.ItemRepository;
import com.suhIT.restroManager.repository.UserRepository;
import com.suhIT.restroManager.service.BillService;
import com.suhIT.restroManager.service.DinnerTableService;
import com.suhIT.restroManager.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImplementation implements BillService {


    private final OrderingService orderingService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final UserMapper userMapper;
    private final DinnerTableService tableService;

    @Autowired
    public BillServiceImplementation(OrderingService orderingService, ItemRepository itemRepository, UserRepository userRepository,
                                     BillRepository billRepository, BillMapper billMapper, UserMapper userMapper, DinnerTableService tableService) {
        this.orderingService = orderingService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.billRepository = billRepository;
        this.billMapper = billMapper;
        this.userMapper = userMapper;
        this.tableService = tableService;
    }
     @Override
    public BillDTO createBill(OrderingDTO orderingDTO) {
         List<OrderedItemDTO> orderedItemDTOS = orderingDTO.getOrderedItemDTOS();
         // check is everyOrderedItem prepared?
         //areAllItemsPrepared(orderedItemDTOS); // it should be isPlaced: true

         // Collecting all Items from Ordered items
         List<Item> itemsFromOrder = new ArrayList<>();
         for (OrderedItemDTO dto : orderedItemDTOS) {
            ItemDTO itemDto = dto.getItem();
             itemsFromOrder.add(itemRepository.findById(itemDto.getId()).orElseThrow(() -> new ItemNotFoundException(
                     HttpStatus.NOT_FOUND, "Item with id " + itemDto.getId() + " not found!")));
         }

         // zameni u Billu i stavi celog usera
//         User waiterInOrder = userRepository.findByUsername(orderingDTO.getWaiter().getUsername()).orElseThrow(
//                 () -> new UserNotFoundException(HttpStatus.NOT_FOUND,
//                         "User with username " + orderingDTO.getWaiter().getUsername() + " not found!"
//                 ));

         Bill newBill = Bill.builder()
                 .waiter(userMapper.toEntity(orderingDTO.getWaiter()))
                 .cook(orderingDTO.getCook() == null ? null : userMapper.toEntity(orderingDTO.getCook()))
                 .bartender(orderingDTO.getBartender() == null ? null : userMapper.toEntity(orderingDTO.getBartender()))
                 .items(itemsFromOrder)
                 .price(orderingDTO.getPrice())
                 .cost(orderingDTO.getCost())
                 .createdAt(LocalDateTime.now())
                 .paid(true)
                 .build();

         //Nakon sto je bill sacuvan u bazu uspesno, onda ides orderingService.deleteByID(orderingId);
         if (billRepository.save(newBill) != null) {
             orderingService.deleteById(orderingDTO.getId());
         }
         this.tableService.setAsFree(orderingDTO.getTableName());

         return billMapper.toDTO(newBill);
    }

    @Override
    public List<BillDTO> getAll() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream().map(billMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BillDTO getById(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(
                () -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Bill not found."));
        return  billMapper.toDTO(bill);

    }


}
