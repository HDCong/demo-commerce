package com.example.demo.mapper;

import com.example.demo.domain.dto.CustomerDto;
import com.example.demo.domain.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ICustomerMapper.class})
public interface ICustomerMapper extends ICommonMapper<CustomerDto, Customer> {

  ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

  @Override
  @Mappings({
      @Mapping(source = "dob", target = "dob", qualifiedByName = "long2Date")
  })
  Customer toEntity(CustomerDto dto);

  @Override
  @Mappings({
      @Mapping(source = "dob", target = "dob", qualifiedByName = "date2Long")
  })
  CustomerDto toDto(Customer entity);
}
