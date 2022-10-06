package ru.kuzmina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kuzmina.model.Role;

public interface RoleRepository extends JpaRepository <Role, Long> {

}
