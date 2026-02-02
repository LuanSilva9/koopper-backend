package com.ekos.koopper.modules.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ekos.koopper.modules.department.Department;
import com.ekos.koopper.modules.department.DepartmentService;
import com.ekos.koopper.modules.user.dto.UserRequestDTO;
import com.ekos.koopper.modules.user.dto.UserRequestEdtiableDTO;
import com.ekos.koopper.shared.BaseCrud;
import com.ekos.koopper.shared.exceptions.custom.ResourceConflictException;

import jakarta.transaction.Transactional;

@Service
public class UserService extends BaseCrud<User, UUID> {
    private final UserRepository userRepository;
    private final DepartmentService departmentService;

    public UserService(UserRepository userRepository, DepartmentService departmentService) {
        this.userRepository = userRepository;
        this.departmentService = departmentService;
    }

    @Override
    protected JpaRepository<User, UUID> getRepository() {
        return this.userRepository;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Transactional
    public User create(UserRequestDTO userRequestDto) {
        if(userRepository.existsByEmail(userRequestDto.email())) throw ResourceConflictException.alreadyExists("Email");

        Department department = departmentService.buscarPorId(userRequestDto.departmentId());

        User newUser = new User(userRequestDto, department);

        return salvar(newUser);
    }

    @Transactional
    public User edit(UUID id, UserRequestEdtiableDTO userRequestEdtiableDto) {
        User user = buscarPorId(id);

        validateEmailUniqueness(user, userRequestEdtiableDto.email());

        user.setUsername(userRequestEdtiableDto.username());
        user.setEmail(userRequestEdtiableDto.email());
        user.setPassword(userRequestEdtiableDto.password());

        return user;
    }


    private void validateEmailUniqueness(User user, String email) {
        if (!email.equals(user.getEmail()) && userRepository.existsByEmailAndIdNot(email, user.getId())) {
            throw new ResourceConflictException("Email");
        }
    }
    
}
