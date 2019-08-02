package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.User;
import pl.wasko.movies_server.repository.UserRepository;

import java.util.Map;
import java.util.TreeMap;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Map<String, Object> findUsersByParams(String name, PageRequest pageRequest) {
        Page<User> usersPage = userRepository.findUsersByNameContainingIgnoreCase(
                name, pageRequest);
        Map<String, Object> result = new TreeMap<>();
        result.put("content", usersPage.getContent());
        result.put("totalElements", usersPage.getTotalElements());
        result.put("totalPages", usersPage.getTotalPages());
        return result;
    }

    public void saveWithPassEncoding(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveWithoutPassEncoding(User user) {
        userRepository.save(user);
    }

    public User findOneById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findOneByName(String name) {
        return userRepository.findOneByName(name);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
