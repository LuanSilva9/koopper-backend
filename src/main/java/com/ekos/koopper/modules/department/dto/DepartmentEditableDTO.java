package com.ekos.koopper.modules.department.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartmentEditableDTO(
    @NotBlank(message = "Nome do departamento é obrigatório") 
    String name
) {}
