package pl.sedzimierz.lovepets.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sedzimierz.lovepets.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findOneByLogin(login)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("Login was not found!"));
    }

    private User createSpringSecurityUser(pl.sedzimierz.lovepets.model.User user) {
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        return new User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
