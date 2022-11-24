package ru.kuzmina.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kuzmina.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Can not be empty")
    private String username;

    @Email
    @NotBlank(message = "Can not be empty")
    private String email;

    @NotBlank(message = "Can not be empty")
    private String password;

    private Set<Role> roles;
}
