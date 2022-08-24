package ru.kuzmina.persist;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class User {
    private Long id;
    @NotBlank(message = "Имя не может быть пустым")
    private  String name;
    @NotBlank
    @Email
    private String email;

    public User(String name) {
        this.name = name;
        this.email = "";
    }

}
