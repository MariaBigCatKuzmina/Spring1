package ru.kuzmina.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@Entity
//@Table(name="cart")
public class Cart {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "update_timestamp", nullable = false)
    private Date updateTimestamp;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    @OneToMany(mappedBy = "product")
    private List<Product> productList;

}
