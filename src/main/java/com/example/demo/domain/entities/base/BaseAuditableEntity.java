package com.example.demo.domain.entities.base;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;

@Getter
@Setter
@MappedSuperclass
public class BaseAuditableEntity implements Serializable {

  @Column(name = "created_at" , updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(name = "created_by" , updatable = false)
  private String createdBy;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @Column(name = "updated_by")
  private String updatedBy;

  @PrePersist
  @PreUpdate
  public void onSave() {
    final Date today = new Date();
    if (Objects.isNull(createdAt)) {
      setCreatedAt(today);
      setCreatedBy(StringUtils.defaultIfBlank(ThreadContext.get("USER_ID"), "System"));
    }
    setUpdatedAt(today);

    setUpdatedBy(StringUtils.defaultIfBlank(ThreadContext.get("USER_ID"), "System"));
  }
}
