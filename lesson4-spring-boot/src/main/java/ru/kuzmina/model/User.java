package ru.kuzmina.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
 //   @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
//    @NotBlank
//    @Email
    private String email;

    @Column(nullable = false, length = 1024)
//    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(String name) {
        this.name = name;
        this.email = "";
    }

}
