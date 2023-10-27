
package com.example.demo.domain.entities;

import com.example.demo.domain.entities.base.BaseAuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "shops")
public class Shop extends BaseAuditableEntity {

  @Id
  @Column
  @GeneratedValue(generator = "uuid4")
  @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column
  private String name;

  @Column
  private String location;

  @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<Product> products = new HashSet<>();

}
