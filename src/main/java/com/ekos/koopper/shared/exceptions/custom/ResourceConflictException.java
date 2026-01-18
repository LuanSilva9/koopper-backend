package com.ekos.koopper.shared.exceptions.custom;

public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(String message) {
        super(message);
    }

    public static ResourceConflictException alreadyExists(String field) {
        return new ResourceConflictException(
            String.format("Já existe um registro com o mesmo %s", field)
        );
    }
}