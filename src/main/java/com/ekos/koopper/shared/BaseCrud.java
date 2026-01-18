package com.ekos.koopper.shared;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseCrud<T, ID> {
    protected abstract JpaRepository<T, ID> getRepository();

    @Transactional
    public T salvar(T entity) {
        return getRepository().save(entity);
    }

    @Transactional(readOnly = true)
    public Optional<T> buscarPorId(ID id) {
        return getRepository().findById(id);
    }

    @Transactional(readOnly = true)
    public List<T> listarTodos() {
        return getRepository().findAll();
    }

    @Transactional
    public void deletar(ID id) {
        getRepository().deleteById(id);
    }
}
