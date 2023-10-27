package com.example.demo.web.rest;

import com.example.demo.domain.dto.CustomerDto;
import com.example.demo.domain.dto.GetAllDto;
import com.example.demo.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
  private final ICustomerService customerService;

  @GetMapping
  public ResponseEntity<GetAllDto<CustomerDto>> getCustomers(Pageable page) {
    return ResponseEntity.ok(customerService.findAllCustomer(page));
  }

  @PostMapping
  public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto request) {
    return ResponseEntity.ok(customerService.createCustomer(request));
  }

  @PostMapping("/import")
  public ResponseEntity<Void> importCustomers(@RequestParam("file") MultipartFile file) {
    customerService.importCustomers(file);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
