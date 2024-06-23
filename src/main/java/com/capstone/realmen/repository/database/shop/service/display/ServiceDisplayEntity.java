package com.capstone.realmen.repository.database.shop.service.display;

import com.capstone.realmen.repository.database.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.FieldDefaults;

@With
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_service_display")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceDisplayEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long serviceDisplayId;
    @Column(name = "shop_service_id")
    Long shopServiceId;
    @Column(name = "service_display_content")
    String serviceDisplayContent;
    
}
