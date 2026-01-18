package com.ekos.koopper.shared.exceptions.custom;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class<?> entityClass) {
        super(entityClass.getSimpleName() + " não encontrado");
    }
}