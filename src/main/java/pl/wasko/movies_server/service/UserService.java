package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.Role;
import pl.wasko.movies_server.model.User;
import pl.wasko.movies_server.repository.RoleRepository;
import pl.wasko.movies_server.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findUsersByParams(String name, PageRequest pageRequest) {
        Page<User> directorsPage = userRepository.findUsersByNameContainingIgnoreCase(
                name, pageRequest);
        return directorsPage.getContent();
    }
    public User saveWithPassEncoding(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
    public User saveWithoutPassEncoding(User user) {
        userRepository.save(user);
        return user;
    }
    public User findOneById(Long id) {
        User userToBeSend = userRepository.findById(id).get();
        return userToBeSend;
    }
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    public boolean userIsAdmin(Long id) {
        Set<Role> userRoles = userRepository.findById(id).get().getRoles();
        for (Role role : userRoles) {
            if (role.getRole().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
