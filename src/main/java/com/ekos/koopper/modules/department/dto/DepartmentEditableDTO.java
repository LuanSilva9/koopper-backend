package com.ekos.koopper.modules.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentEditableDTO(
    @NotBlank(message = "Nome do departamento é obrigatório") 
    String name,
    
    @NotNull 
    Boolean allowCrossDepartmentView
) {}
