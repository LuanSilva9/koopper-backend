package com.ekos.koopper.modules.tenant.dto;

import jakarta.validation.constraints.NotBlank;

public record TenantRequestDTO(
    @NotBlank String name,
    @NotBlank String domain,
    @NotBlank String cnpj
) {
}
