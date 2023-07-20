package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.OrderingDTO;

public interface OrderingService {

    OrderingDTO createOrder(OrderingDTO orderDto);
    OrderingDTO getById(Long id);


    void deleteById(Long id);
}
