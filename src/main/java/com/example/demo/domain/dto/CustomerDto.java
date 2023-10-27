package com.example.demo.domain.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
  private String id;
  private String name;
  private Long dob;
  private String email;
}
