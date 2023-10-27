package com.example.demo.mapper;

import com.example.demo.domain.dto.CustomerDto;
import com.example.demo.domain.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ICustomerMapper extends ICommonMapper<CustomerDto, Customer> {
  ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

}
