package com.ekos.koopper.modules.tenant;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ekos.koopper.modules.tenant.dto.TenantRequestDTO;
import com.ekos.koopper.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tenant")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Tenant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, unique = true, length = 120)
    private String domain;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false, unique = false)
    private boolean allowCrossDepartmentView = false;

    private LocalDateTime expiresDateContract;

    public Tenant(TenantRequestDTO tenantDto) {
        this.name = tenantDto.name();
        this.domain = tenantDto.domain();
        this.cnpj = tenantDto.cnpj();
        this.active = true;
        this.expiresDateContract = LocalDateTime.now().plusYears(1);
    }
}
