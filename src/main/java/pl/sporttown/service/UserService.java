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


    public void addUsers() {
        User user1 = new User();
        user1.setName("Emila");
        user1.setLastName("Trzcińska");
        user1.setEmail("eatrzcinska@gmail.com");
        user1.setNick("Emila");
        user1.setPassword("$2a$10$0u0xT.PjG.nZqGem31btOO/.2qhbra82T70Pw2oi220u0lxzAy89W");
        repository.save(user1);

        User user2 = new User();
        user2.setName("Gosia");
        user2.setLastName("Bąk");
        user2.setEmail("gosia@gmail.com");
        user2.setNick("Gosia");
        user2.setPassword("$2a$10$0u0xT.PjG.nZqGem31btOO/.2qhbra82T70Pw2oi220u0lxzAy89W");
        repository.save(user2);

        User user3 = new User();
        user3.setName("Paweł");
        user3.setLastName("Ościłowicz");
        user3.setEmail("pawel@gmail.com");
        user3.setNick("Paweł");
        user3.setPassword("$2a$10$0u0xT.PjG.nZqGem31btOO/.2qhbra82T70Pw2oi220u0lxzAy89W");
        repository.save(user3);
    }
}
