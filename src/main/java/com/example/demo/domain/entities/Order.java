
package com.example.demo.domain.entities;

import com.example.demo.domain.entities.base.BaseAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
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
@Table(name = "orders")
public class Order extends BaseAuditableEntity {

  @Id
  @Column
  @GeneratedValue(generator = "uuid4")
  @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column
  private Date deliveryDate;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Transient
  private BigDecimal totalPrice;

}
