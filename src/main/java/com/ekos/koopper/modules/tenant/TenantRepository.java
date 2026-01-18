package com.ekos.koopper.modules.tenant;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    /* CONSULTAS TENANT */
    boolean existsByCnpjAndIdNot(String cnpj, UUID id);
}
