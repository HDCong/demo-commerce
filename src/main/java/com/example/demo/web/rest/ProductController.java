package com.example.demo.web.rest;

import com.example.demo.domain.dto.GetAllDto;
import com.example.demo.domain.dto.ProductDto;
import com.example.demo.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
  private final IProductService productService;

  @GetMapping
  public ResponseEntity<GetAllDto<ProductDto>> getProducts(Pageable page) {
    return ResponseEntity.ok(productService.findAllProduct(page));
  }

  @PostMapping
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto request) {
    return ResponseEntity.ok(productService.createProduct(request));
  }

  @PostMapping("/import")
  public ResponseEntity<Void> importProducts(@RequestParam("file") MultipartFile file) {
    productService.importProducts(file);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
