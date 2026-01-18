package com.ekos.koopper.modules.tenant;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ekos.koopper.modules.tenant.dto.TenantRequestDTO;
import com.ekos.koopper.modules.tenant.dto.TenantRequestEditableDTO;
import com.ekos.koopper.shared.BaseCrud;

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

    public Tenant create(TenantRequestDTO tenantDto) {
        Tenant newTenant = new Tenant(tenantDto);

        return salvar(newTenant);
    }

    public Tenant edit(UUID id, TenantRequestEditableDTO tenantDto) {
        Tenant tenant = buscarPorId(id).orElseThrow(RuntimeException::new);

        if(!tenantDto.cnpj().equals(tenant.getCnpj()) && tenantRepository.existsByCnpjAndIdNot(tenantDto.cnpj(), id)) {
            throw new RuntimeException("CNPJ já cadastrado");
        };

        tenant.setName(tenantDto.name());
        tenant.setCnpj(tenantDto.cnpj());
        tenant.setAllowCrossDepartmentView(tenantDto.allowCrossDepartmentView());

        return salvar(tenant);
    }
}
