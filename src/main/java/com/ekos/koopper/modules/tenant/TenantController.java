package com.ekos.koopper.modules.tenant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import com.ekos.koopper.modules.tenant.dto.TenantRequestDTO;
import com.ekos.koopper.modules.tenant.dto.TenantRequestEditableDTO;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> listById(@PathVariable UUID id) {
        Tenant tenant = tenantService.buscarPorId(id).orElseThrow(RuntimeException::new);
        
        return ResponseEntity.ok().body(tenant);
    }
    

    @GetMapping
    public ResponseEntity<List<Tenant>> list() {
        return ResponseEntity.ok().body(tenantService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Tenant> create(@RequestBody TenantRequestDTO tenantDto) {
        Tenant tenantCreated = tenantService.create(tenantDto);

        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(tenantCreated.getId())
        .toUri();

        return ResponseEntity.created(uri).body(tenantCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tenant> edit(@PathVariable UUID id, @Valid @RequestBody TenantRequestEditableDTO tenantDto) {
        return ResponseEntity.ok().body(tenantService.edit(id, tenantDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        tenantService.deletar(id);

        return ResponseEntity.noContent().build();
    }    
    
}
