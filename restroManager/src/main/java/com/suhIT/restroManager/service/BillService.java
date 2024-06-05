package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.BillDTO;
import com.suhIT.restroManager.dto.OrderingDTO;

import java.util.List;

public interface BillService {


    BillDTO createBill(OrderingDTO orderingDTO);

    List<BillDTO> getAll();

    BillDTO getById(Long billId);
}
