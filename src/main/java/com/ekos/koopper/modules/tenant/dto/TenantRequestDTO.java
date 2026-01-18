package com.ekos.koopper.modules.tenant.dto;

public record TenantRequestDTO(String name, String domain, String cnpj, boolean allowCrossDepartmentView) {
    
}
