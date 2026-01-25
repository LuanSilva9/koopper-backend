package com.ekos.koopper.modules.department;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ekos.koopper.modules.department.dto.DepartmentEditableDTO;
import com.ekos.koopper.modules.department.dto.DepartmentRequestDTO;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable UUID id) {
        Department departmentSelected = departmentService.buscarPorId(id);

        return ResponseEntity.ok().body(departmentSelected);
    }

    @PostMapping
    public ResponseEntity<Department> create(@Valid @RequestBody DepartmentRequestDTO departmentRequestDto) {
        Department departmentCreated = departmentService.create(departmentRequestDto);
        
        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(departmentCreated.getId())
        .toUri();

        return ResponseEntity.created(uri).body(departmentCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> edit(@PathVariable UUID id, @Valid @RequestBody DepartmentEditableDTO departmentEditableDto) {
        return ResponseEntity.ok().body(departmentService.edit(id, departmentEditableDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        departmentService.deletar(id);

        return ResponseEntity.noContent().build();
    }
    
}