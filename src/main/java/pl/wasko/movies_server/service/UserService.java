package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.Role;
import pl.wasko.movies_server.model.User;
import pl.wasko.movies_server.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findUsersByParams(String name, PageRequest pageRequest) {
        Page<User> directorsPage = userRepository.findUsersByNameContainingIgnoreCase(
                name, pageRequest);
        return directorsPage.getContent();
    }

    public void saveWithPassEncoding(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveWithoutPassEncoding(User user) {
        userRepository.save(user);
    }

    public User findOneById(Long id) {
        return userRepository.findById(id).get();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
