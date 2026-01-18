package com.ekos.koopper.shared;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ekos.koopper.shared.exceptions.custom.ResourceNotFoundException;

public abstract class BaseCrud<T, ID> {
    protected abstract JpaRepository<T, ID> getRepository();
    protected abstract Class<T> getEntityClass();

    @Transactional
    public T salvar(T entity) {
        return getRepository().save(entity);
    }

    @Transactional(readOnly = true)
    public T buscarPorId(ID id) {
        return getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException(getEntityClass()));
    }

    @Transactional(readOnly = true)
    public List<T> listarTodos() {
        return getRepository().findAll();
    }

    @Transactional
    public void deletar(ID id) {
        T entity = buscarPorId(id);

        getRepository().delete(entity);
    }
}
