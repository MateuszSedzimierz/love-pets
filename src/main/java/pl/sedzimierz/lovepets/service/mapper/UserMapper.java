package pl.sedzimierz.lovepets.service.mapper;

import org.springframework.stereotype.Service;
import pl.sedzimierz.lovepets.model.Authority;
import pl.sedzimierz.lovepets.model.User;
import pl.sedzimierz.lovepets.service.dto.UserDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper implements BaseMapper<User, UserDTO> {

    private final AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public User mapToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setLogin(userDTO.getLogin());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setAddress(addressMapper.mapToEntity(userDTO.getAddressDTO()));
            user.setAuthorities(getAuthoritiesFromStrings(userDTO.getAuthorities()));
            return user;
        }
    }

    private Set<Authority> getAuthoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();
        if (authoritiesAsString != null) {
            authorities = authoritiesAsString.stream().map(name -> {
                Authority authority = new Authority();
                authority.setName(name);
                return authority;
            }).collect(Collectors.toSet());
        }
        return authorities;
    }

    @Override
    public UserDTO mapToDTO(User user) {
        return new UserDTO(user);
    }
}
