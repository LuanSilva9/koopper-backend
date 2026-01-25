package com.ekos.koopper.modules.department.dto;

import java.util.UUID;

import com.ekos.koopper.modules.tenant.dto.TenantResponseDTO;

public record DepartmentResponseDTO(UUID id, String name, TenantResponseDTO tenant) {
    
}
