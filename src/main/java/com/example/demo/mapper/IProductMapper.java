package com.example.demo.mapper;

import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IProductMapper extends ICommonMapper<ProductDto, Product> {
  IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
}
