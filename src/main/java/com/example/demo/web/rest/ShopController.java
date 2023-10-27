package com.example.demo.web.rest;

import com.example.demo.domain.dto.GetAllDto;
import com.example.demo.domain.dto.ShopDto;
import com.example.demo.service.IProductService;
import com.example.demo.service.IShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/shops")
public class ShopController {

  private final IShopService shopService;
  private final IProductService productService;

  @GetMapping
  public ResponseEntity<GetAllDto<ShopDto>> getShop(Pageable page) {
    return ResponseEntity.ok(shopService.findAllShop(page));
  }

  @PostMapping
  public ResponseEntity<ShopDto> createProduct(@RequestBody ShopDto request) {
    return ResponseEntity.ok(shopService.createShop(request));
  }

  @PostMapping("/import")
  public ResponseEntity<Void> importShop(@RequestParam("file") MultipartFile file) {
    shopService.importShops(file);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{shopId}/products/import")
  public ResponseEntity<Void> importProducts(
      @RequestParam("file") MultipartFile file,
      @PathVariable("shopId") String shopId
  ) {
    productService.importProducts(file, shopId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
