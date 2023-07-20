package com.suhIT.restroManager.mapper;


import com.suhIT.restroManager.dto.BillDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BillMapper implements Mapper<Bill, BillDTO> {

    private final ItemMapper itemMapper;

    @Autowired
    public BillMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public Bill toEntity(BillDTO billDTO) {
        return null;
    }

    @Override
    public BillDTO toDTO(Bill bill) {
        List<ItemDTO> itemDTOs = bill.getItems().stream().map(itemMapper::toDTO).collect(Collectors.toList());
        return BillDTO.builder()
                .id(bill.getId())
                .waiter(bill.getWaiter())
                .items(itemDTOs)
                .price(bill.getPrice())
                .cost(bill.getCost())
                .createdAt(bill.getCreatedAt())
                .paid(bill.isPaid())
                .build();
    }
}
