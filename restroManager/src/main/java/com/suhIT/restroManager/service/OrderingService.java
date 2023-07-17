package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.OrderingDTO;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.Ordering;

import java.util.List;

public interface OrderingService {

    Ordering createOrder(OrderingDTO orderDto);


}
