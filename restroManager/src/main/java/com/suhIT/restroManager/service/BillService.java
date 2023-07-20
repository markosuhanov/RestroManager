package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.BillDTO;
import com.suhIT.restroManager.dto.OrderingDTO;

public interface BillService {


    BillDTO createBill(OrderingDTO orderingDTO);
}
