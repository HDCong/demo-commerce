package com.example.demo.service;

import com.example.demo.domain.dto.CustomerDto;
import com.example.demo.domain.dto.GetAllDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ICustomerService {

  GetAllDto<CustomerDto> findAllCustomer(Pageable page);

  CustomerDto createCustomer(CustomerDto request);

  void importCustomers(MultipartFile file);
}
