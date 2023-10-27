package com.example.demo.service.implementation;

import com.example.demo.repository.IOrderRepository;
import com.example.demo.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {
  private final IOrderRepository orderRepository;

}
