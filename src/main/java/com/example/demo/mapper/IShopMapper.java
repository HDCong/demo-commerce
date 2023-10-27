package com.example.demo.mapper;

import com.example.demo.domain.dto.ShopDto;
import com.example.demo.domain.entities.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IShopMapper extends ICommonMapper<ShopDto, Shop> {
  IShopMapper INSTANCE = Mappers.getMapper(IShopMapper.class);

}
