package ru.kuzmina.services;

import org.springframework.stereotype.Service;
import ru.kuzmina.model.Dto.UserDto;
import ru.kuzmina.model.User;
import ru.kuzmina.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(this::dtoFromUser);
    }

    public List<UserDto> findAllByFilter(String usernameFilter, String emailFilter) {
        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : '%' + usernameFilter.trim() + '%';
        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : '%' + emailFilter.trim() + '%';
        return userRepository.userByUsernameAndEmail(usernameFilter, emailFilter).stream()
                .map(this::dtoFromUser)
                .toList();
    }

    public List<User> findUsersByFilter(String usernameFilter, String emailFilter) {
        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : '%' + usernameFilter.trim() + '%';
        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : '%' + emailFilter.trim() + '%';
        return userRepository.userByUsernameAndEmail(usernameFilter, emailFilter);
    }

    public UserDto save(UserDto user) {
        User save = userRepository.save(new User(user.getName(), user.getEmail(), user.getPassword()));
        return dtoFromUser(save);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto dtoFromUser(User user) {
        if (user != null) {
            return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        }
        return null;
    }
}
