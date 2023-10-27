package com.example.demo.service;

import com.example.demo.domain.dto.GetAllDto;
import com.example.demo.domain.dto.ShopDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IShopService {
  GetAllDto<ShopDto> findAllShop(Pageable page);

  ShopDto createShop(ShopDto request);

  void importShops(MultipartFile file);
}
