package com.ekos.koopper.modules.department;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ekos.koopper.modules.department.dto.DepartmentEditableDTO;
import com.ekos.koopper.modules.department.dto.DepartmentRequestDTO;
import com.ekos.koopper.modules.tenant.Tenant;
import com.ekos.koopper.modules.tenant.TenantService;
import com.ekos.koopper.shared.BaseCrud;

import jakarta.transaction.Transactional;

@Service
public class DepartmentService extends BaseCrud<Department, UUID> {
    private final DepartmentRepository departmentRepository;
    private final TenantService tenantService;

    public DepartmentService(DepartmentRepository departmentRepository, TenantService tenantService) {
        this.departmentRepository = departmentRepository;
        this.tenantService = tenantService;
    }

    @Override
    protected JpaRepository<Department, UUID> getRepository() {
        return departmentRepository;
    }

    @Override
    protected Class<Department> getEntityClass() {
        return Department.class;
    }

    @Transactional
    public Department create(DepartmentRequestDTO departmentRequestDto) {
        Tenant tenant = tenantService.buscarPorId(departmentRequestDto.tenantId());
        
        Department newDepartment = new Department(departmentRequestDto, tenant);
        
        return salvar(newDepartment);
    }

    @Transactional
    public Department edit(UUID id, DepartmentEditableDTO departmentEditableDto) {
        Department department = buscarPorId(id);

        department.setName(departmentEditableDto.name());
        department.setAllowCrossDepartmentView(departmentEditableDto.allowCrossDepartmentView());

        return department;
    }
}
