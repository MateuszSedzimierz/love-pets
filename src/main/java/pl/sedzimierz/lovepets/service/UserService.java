package pl.sedzimierz.lovepets.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sedzimierz.lovepets.model.Authority;
import pl.sedzimierz.lovepets.model.User;
import pl.sedzimierz.lovepets.repository.AuthorityRepository;
import pl.sedzimierz.lovepets.repository.UserRepository;
import pl.sedzimierz.lovepets.security.AuthoritiesConstants;
import pl.sedzimierz.lovepets.service.dto.UserDTO;
import pl.sedzimierz.lovepets.service.exception.EmailAlreadyUsedException;
import pl.sedzimierz.lovepets.service.exception.LoginAlreadyUsedException;
import pl.sedzimierz.lovepets.service.mapper.UserMapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(UserDTO userDTO, String password) {
        checkIfLoginExist(userDTO.getLogin());
        checkIfEmailExist(userDTO.getEmail());

        User user = userMapper.mapToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(password));
        Set<Authority> authorities = new HashSet<>();
        authorityRepository
                .findByName(AuthoritiesConstants.USER)
                .ifPresent(authorities::add);
        user.setAuthorities(authorities);
        userRepository.save(user);
        log.info("Created user: {}", user);
    }

    private void checkIfLoginExist(String login) {
        if (userRepository.findOneByLogin(login).isPresent()) {
            log.debug("Login '{}' exists in database", login);
            throw new LoginAlreadyUsedException();
        }
    }

    private void checkIfEmailExist(String email) {
        if (userRepository.findOneByEmail(email).isPresent()) {
            log.debug("Email '{}' exists in database", email);
            throw new EmailAlreadyUsedException();
        }
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserByLogin(String login) {
        return userRepository
                .findOneByLogin(login)
                .map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(UserDTO::new);
    }

    @Transactional
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
                    .findById(userDTO.getId())
                    .map(user -> {
                        if (!user.getEmail().equals(userDTO.getEmail())) {
                            checkIfEmailExist(userDTO.getEmail());
                        }
                        User updatedUser = userMapper.mapToEntity(userDTO);
                        updatedUser.setPassword(user.getPassword());
                        updatedUser.setAuthorities(user.getAuthorities());
                        updatedUser.setPets(user.getPets());
                        userRepository.save(updatedUser);
                        log.info("Updated user: {}", updatedUser);
                        return new UserDTO(updatedUser);
                    }).orElse(new UserDTO()));
    }
}
