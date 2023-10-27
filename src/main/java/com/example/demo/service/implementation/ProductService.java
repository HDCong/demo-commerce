package com.example.demo.service.implementation;

import com.example.demo.domain.dto.GetAllDto;
import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.entities.Product;
import com.example.demo.mapper.IProductMapper;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IFileReader;
import com.example.demo.service.IProductService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

  private final IProductRepository productRepository;
  private final IFileReader excelReader;

  @Override
  public GetAllDto<ProductDto> findAllProduct(Pageable page) {
    var result = productRepository.findAll(page);
    if (!result.hasContent()) {
      return new GetAllDto<>();
    }
    return GetAllDto.<ProductDto>builder()
        .total(result.getTotalElements())
        .content(result.getContent().stream().map(IProductMapper.INSTANCE::toDto).toList())
        .build();
  }

  @Override
  public ProductDto createProduct(ProductDto request) {
    final Product persisted = productRepository.save(IProductMapper.INSTANCE.toEntity(request));
    return IProductMapper.INSTANCE.toDto(persisted);
  }

  @Override
  @Transactional
  public void importProducts(MultipartFile file) {
    final List<ProductDto> productDtos = excelReader.readImportProductFile(file, null);
    productRepository.saveAll(productDtos.stream().map(IProductMapper.INSTANCE::toEntity).toList());
  }

  @Override
  @Transactional
  public void importProducts(MultipartFile file, String shopId) {
    final List<ProductDto> productDtos = excelReader.readImportProductFile(file, shopId);
    productRepository.saveAll(productDtos.stream().map(IProductMapper.INSTANCE::toEntity).toList());
  }
}
