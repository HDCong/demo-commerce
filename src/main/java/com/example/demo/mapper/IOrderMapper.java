package com.example.demo.mapper;

import com.example.demo.domain.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

@Mapper
public interface IOrderMapper extends ICommonMapper<OrderDto, Order> {
  IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);

}
