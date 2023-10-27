package com.example.demo.service;

import com.example.demo.domain.dto.GetAllDto;
import com.example.demo.domain.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {

  GetAllDto<ProductDto> findAllProduct(Pageable page);

  ProductDto createProduct(ProductDto request);

  void importProducts(MultipartFile file);

  void importProducts(MultipartFile file, String shopId);
}
