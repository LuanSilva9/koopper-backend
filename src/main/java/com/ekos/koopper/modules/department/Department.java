package com.ekos.koopper.modules.department;

import java.util.UUID;

import com.ekos.koopper.modules.department.dto.DepartmentRequestDTO;
import com.ekos.koopper.modules.tenant.Tenant;
import com.ekos.koopper.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id", callSuper = false)
@ToString
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, length = 120)
    private String name;
    
    @Column(nullable = false, unique = false)
    private boolean allowCrossDepartmentView;


    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    public Department(DepartmentRequestDTO departmentDto, Tenant tenant) {
        this.name = departmentDto.name();
        this.tenant = tenant;
    }
}
