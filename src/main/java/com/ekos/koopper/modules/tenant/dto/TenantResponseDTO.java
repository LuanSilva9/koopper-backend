package com.ekos.koopper.modules.tenant.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TenantResponseDTO(UUID id, String name, String domain, String cnpj, boolean active, LocalDateTime expiresDateContract, boolean allowCrossDepartmentView) {
    
}
