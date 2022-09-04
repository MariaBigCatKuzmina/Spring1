package ru.kuzmina.model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    private User user;

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Contact{" +
                 type +
                ": " + value +
                '}';
    }
}
