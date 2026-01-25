package com.ekos.koopper.modules.tenant.dto;

import jakarta.validation.constraints.NotBlank;

public record TenantRequestEditableDTO(@NotBlank String name, @NotBlank String cnpj, @NotBlank boolean allowCrossDepartmentView) {
    
}
