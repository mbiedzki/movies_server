package pl.wasko.movies_server.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.wasko.movies_server.model.User;
import pl.wasko.movies_server.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> findByNamePageable(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            HttpServletResponse response) {
        response.setContentType("application/json");
        List<User> listToBeReturned = userService.findUsersByParams(name, of(page, size, Sort.by("name")));
        for (User user : listToBeReturned) {
            user.setPassword("XXXXX");
        }
        return listToBeReturned;
    }

    //find one by id
    @GetMapping("/{id}")
    public User findByById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        User userToBeReturned = userService.findOneById(id);
        userToBeReturned.setPassword("XXXXX");
        return userToBeReturned;
    }

    @PostMapping("")
    public User add(@RequestBody User user, HttpServletResponse response) {
        User userToBeAdded = new User();
        if (user.getPassword().isEmpty()) {
            response.setStatus(400);
            return user;
        }
        //set filed values with received ones
        userToBeAdded.setName(user.getName());
        userToBeAdded.setActive(user.isActive());
        userToBeAdded.setRoles(user.getRoles());
        userToBeAdded.setPassword(user.getPassword());
        userService.saveWithPassEncoding(userToBeAdded);
        userToBeAdded.setPassword("hasło zostało zaszyfrowane");
        return userToBeAdded;
    }

    //update one by id
    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) {
        //find user in db
        User userToBeUpdated = userService.findOneById(id);
        //set filed values with received ones
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setActive(user.isActive());
        userToBeUpdated.setRoles(user.getRoles());
        //if client entered new password then save user with password encoded
        //if password was not input (changed) then save user with current password without encoding
        if (!user.getPassword().isEmpty()) {
            userToBeUpdated.setPassword(user.getPassword());
            userService.saveWithPassEncoding(userToBeUpdated);
            user.setPassword("hasło zostało zaszyfrowane");
            return user;
        } else {
            userService.saveWithoutPassEncoding(userToBeUpdated);
            user.setPassword("hasło bez zmian");
            return user;
        }
    }

    //delete one by id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        userService.delete(id);
        response.setStatus(204);
    }
}
