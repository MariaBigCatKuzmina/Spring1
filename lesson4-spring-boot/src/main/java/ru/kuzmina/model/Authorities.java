package ru.kuzmina.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles Authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}



