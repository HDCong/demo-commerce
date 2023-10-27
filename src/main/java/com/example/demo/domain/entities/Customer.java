package com.example.demo.domain.entities;

import com.example.demo.domain.entities.base.BaseAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends BaseAuditableEntity {

  @Id
  @Column
  @GeneratedValue(generator = "uuid4")
  @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column
  private String fullName;

  @Column
  private Date dob;

  @Column
  private String email;

}
