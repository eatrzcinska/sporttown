package pl.sporttown.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sporttown.controller.modelDTO.UserDTO;
import pl.sporttown.controller.modelDTO.UserEditDTO;
import pl.sporttown.controller.modelDTO.UserRegistrationDto;
import pl.sporttown.domain.model.Role;
import pl.sporttown.domain.model.User;
import pl.sporttown.domain.repoository.UserRepository;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MappingService mappingService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid nick or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getNick(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByNick(String nick) {
        return repository.findByNick(nick);
    }

    public User save(UserRegistrationDto userRegistrationDto) {

        User user = mappingService.mappingUserDTOToUser(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return repository.save(user);
    }

    public void editProfile(UserDTO userDTO, Principal principal) {
        User user = repository.findByNick(principal.getName());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        repository.save(user);
    }
}
