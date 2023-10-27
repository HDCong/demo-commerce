package com.example.demo.service;

import com.example.demo.domain.dto.CustomerDto;
import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.dto.ShopDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IFileReader {
  List<CustomerDto> readImportCustomerFile(MultipartFile file);

  List<ShopDto> readImportShopFile(MultipartFile file);

  List<ProductDto> readImportProductFile(MultipartFile file, String shopId);

}
