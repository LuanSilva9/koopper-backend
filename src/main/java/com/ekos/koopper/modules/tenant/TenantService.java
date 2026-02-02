package com.ekos.koopper.modules.tenant;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ekos.koopper.modules.tenant.dto.TenantRequestDTO;
import com.ekos.koopper.modules.tenant.dto.TenantRequestEditableDTO;
import com.ekos.koopper.shared.BaseCrud;
import com.ekos.koopper.shared.exceptions.custom.ResourceConflictException;

@Service
public class TenantService extends BaseCrud<Tenant, UUID> {
    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public TenantRepository getRepository() {
        return tenantRepository;
    }

    @Override
    protected Class<Tenant> getEntityClass() {
        return Tenant.class;
    }


    public Tenant create(TenantRequestDTO tenantDto) {
        if(tenantRepository.existsByCnpj(tenantDto.cnpj())) throw ResourceConflictException.alreadyExists("Cnpj");

        Tenant newTenant = new Tenant(tenantDto);

        return salvar(newTenant);
    }

    public Tenant edit(UUID id, TenantRequestEditableDTO tenantDto) {
        Tenant tenant = buscarPorId(id);

        validateCnpjUniqueness(tenant, tenantDto.cnpj());

        tenant.setName(tenantDto.name());
        tenant.setCnpj(tenantDto.cnpj());

        return salvar(tenant);
    }


    private void validateCnpjUniqueness(Tenant tenant, String cnpj) {
        if (!cnpj.equals(tenant.getCnpj()) && tenantRepository.existsByCnpjAndIdNot(cnpj, tenant.getId())) {
            throw ResourceConflictException.alreadyExists("Cnpj");
        }
    }
}
