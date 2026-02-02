package com.ekos.koopper.modules.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ekos.koopper.modules.user.dto.UserRequestDTO;
import com.ekos.koopper.modules.user.dto.UserRequestEdtiableDTO;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(userService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserRequestDTO userRequestDto) {
        User userCreated = userService.create(userRequestDto);
        
        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/user")
        .buildAndExpand(userCreated.getId())
        .toUri();

        return ResponseEntity.created(uri).body(userCreated);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> edit(@PathVariable UUID id, @RequestBody UserRequestEdtiableDTO userRequestEdtiableDto) {
        return ResponseEntity.ok().body(userService.edit(id, userRequestEdtiableDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.deletar(id);

        return ResponseEntity.noContent().build();
    }
    
}
