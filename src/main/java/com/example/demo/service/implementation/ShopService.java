package com.example.demo.service.implementation;

import com.example.demo.domain.dto.GetAllDto;
import com.example.demo.domain.dto.ShopDto;
import com.example.demo.domain.entities.Shop;
import com.example.demo.mapper.IShopMapper;
import com.example.demo.repository.IShopRepository;
import com.example.demo.service.IFileReader;
import com.example.demo.service.IShopService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ShopService implements IShopService {
  private final IShopRepository shopRepository;
  private final IFileReader excelReader;

  @Override
  public GetAllDto<ShopDto> findAllShop(Pageable page) {
    var result = shopRepository.findAll(page);
    if (!result.hasContent()) {
      return new GetAllDto<>();
    }
    return GetAllDto.<ShopDto>builder()
        .total(result.getTotalElements())
        .content(result.getContent().stream().map(IShopMapper.INSTANCE::toDto).toList())
        .build();
  }

  @Override
  @Transactional
  public ShopDto createShop(ShopDto request) {
    final Shop persisted = shopRepository.save(IShopMapper.INSTANCE.toEntity(request));
    return IShopMapper.INSTANCE.toDto(persisted);
  }

  @Override
  @Transactional
  public void importShops(MultipartFile file) {
    final List<ShopDto> productDtos = excelReader.readImportShopFile(file);
    shopRepository.saveAll(productDtos.stream().map(IShopMapper.INSTANCE::toEntity).toList());
  }
}
