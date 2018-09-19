package pl.sporttown.service;

import org.springframework.stereotype.Service;
import pl.sporttown.domain.repoository.UserRepository;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

}
