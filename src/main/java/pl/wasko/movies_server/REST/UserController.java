package pl.wasko.movies_server.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.wasko.movies_server.model.Role;
import pl.wasko.movies_server.model.User;
import pl.wasko.movies_server.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.domain.PageRequest.of;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> findUsersByNamePageable(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            HttpServletResponse response) {
        response.setContentType("application/json");
        return userService.findUsersByParams(name, of(page, size, Sort.by("name")));
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) {
        return userService.saveWithPassEncoding(user);
    }

    //find one by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        return userService.findOneById(id);
    }
    //update one by id
    @PutMapping("/{id}")
    public User updateUserById(@RequestBody User user, @PathVariable Long id) {
        //find director in db
        User userToBeUpdated = userService.findOneById(id);
        //set filed values with received ones
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setActive(user.isActive());
        userToBeUpdated.setRoles(user.getRoles());
        return userService.saveWithoutPassChange(userToBeUpdated);
    }
    //delete one by id
    @DeleteMapping("/{id}")
    public void deleteDirectorById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        userService.delete(id);
        response.setStatus(204);
    }
}
