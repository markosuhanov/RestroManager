package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.BillDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.dto.OrderedItemDTO;
import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.exception.ItemNotFoundException;
import com.suhIT.restroManager.exception.NotAllItemsPreparedException;
import com.suhIT.restroManager.mapper.BillMapper;
import com.suhIT.restroManager.model.Bill;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.repository.BillRepository;
import com.suhIT.restroManager.repository.ItemRepository;
import com.suhIT.restroManager.repository.UserRepository;
import com.suhIT.restroManager.service.BillService;
import com.suhIT.restroManager.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImplementation implements BillService {


    private final OrderingService orderingService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    @Autowired
    public BillServiceImplementation(OrderingService orderingService, ItemRepository itemRepository, UserRepository userRepository,
                                     BillRepository billRepository, BillMapper billMapper) {
        this.orderingService = orderingService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.billRepository = billRepository;
        this.billMapper = billMapper;
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
                 .waiter(orderingDTO.getWaiter().getUsername())
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

        return billMapper.toDTO(newBill);
    }



}
