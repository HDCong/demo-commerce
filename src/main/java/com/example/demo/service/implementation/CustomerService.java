package com.example.demo.service.implementation;

import com.example.demo.domain.dto.CustomerDto;
import com.example.demo.domain.dto.GetAllDto;
import com.example.demo.domain.entities.Customer;
import com.example.demo.mapper.ICustomerMapper;
import com.example.demo.repository.ICustomerRepository;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IFileReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerService {

  private final ICustomerRepository customerRepository;
  private final IFileReader excelReader;

  @Override
  public GetAllDto<CustomerDto> findAllCustomer(Pageable page) {
    var result = customerRepository.findAll(page);
    if (!result.hasContent()) {
      return new GetAllDto<>();
    }
    return GetAllDto.<CustomerDto>builder()
        .total(result.getTotalElements())
        .content(result.getContent().stream().map(ICustomerMapper.INSTANCE::toDto).toList())
        .build();  }

  @Override
  public CustomerDto createCustomer(CustomerDto request) {
    final Customer persisted = customerRepository.save(ICustomerMapper.INSTANCE.toEntity(request));
    return ICustomerMapper.INSTANCE.toDto(persisted);
  }

  @Override
  public void importCustomers(MultipartFile file) {
    final List<CustomerDto> productDtos = excelReader.readImportCustomerFile(file);
    customerRepository.saveAll(productDtos.stream().map(ICustomerMapper.INSTANCE::toEntity).toList());
  }
}
