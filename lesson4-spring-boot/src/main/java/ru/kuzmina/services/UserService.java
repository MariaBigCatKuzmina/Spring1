package ru.kuzmina.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kuzmina.model.Dto.UserDto;
import ru.kuzmina.model.User;
import ru.kuzmina.model.mapper.UserDtoMapper;
import ru.kuzmina.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper mapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDtoMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(mapper::map);
    }

    public List<UserDto> findUsersByFilter(String usernameFilter, String emailFilter) {
        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : '%' + usernameFilter.trim() + '%';
        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : '%' + emailFilter.trim() + '%';
        return userRepository.userByUsernameAndEmail(usernameFilter, emailFilter).stream()
                .map(mapper::map)
                .toList();
    }

    public UserDto save(UserDto userDto) {
        User user = mapper.map(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.map(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public org.springframework.security.core.userdetails.User findUserByName(String username) {
       return userRepository.findUserByName(username)
               .map(user -> new org.springframework.security.core.userdetails.User(
                       user.getName(),
                       user.getPassword(),
                       Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
               )).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
