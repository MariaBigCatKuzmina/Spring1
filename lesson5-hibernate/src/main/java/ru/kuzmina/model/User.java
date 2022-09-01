package ru.kuzmina.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 1024)
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    private List<Contact> contacts;

    //это сделала чтобы попробовать связь многие-ко-многим
    @ManyToMany
    @JoinTable(name = "Products_Users",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(String name, String password, List<Contact> contacts) {
        this.name = name;
        this.contacts = contacts;
        this.password = password;
    }

    public User(String name, String password, List<Contact> contacts, List<Product> products) {
        this(name, password, contacts);
        this.products = products;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
