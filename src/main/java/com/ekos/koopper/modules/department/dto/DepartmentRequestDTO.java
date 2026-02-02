package com.ekos.koopper.modules.department.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentRequestDTO(
    @NotBlank(message = "O nome do departamento é obrigatório")
    String name,
    
    @NotNull(message = "Você tem que especificar a permissao desse departamento.")
    Boolean allowCrossDepartmentView,

    @NotNull(message = "O tenantId é obrigatório")
    UUID tenantId
) {}