package com.ekos.koopper.modules.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmailAndIdNot(String email, UUID id);
    boolean existsByEmail(String email);
}
