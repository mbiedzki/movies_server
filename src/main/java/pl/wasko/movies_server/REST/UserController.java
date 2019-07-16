package pl.wasko.movies_server.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.wasko.movies_server.model.User;
import pl.wasko.movies_server.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.PageRequest.of;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public Map findByNamePageable(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            HttpServletResponse response) {
        response.setContentType("application/json");
        Map listToBeReturned = userService.findUsersByParams(name, of(page, size, Sort.by("name")));
        return listToBeReturned;
    }

    @GetMapping("/findOne")
    public User findOneByName(@RequestParam String name, HttpServletResponse response){
        response.setContentType("application/json");
        User userToBeReturned = userService.findOneByName(name);
        userToBeReturned.setPassword("");
        return userToBeReturned;
    }

    //find one by id
    @GetMapping("/{id}")
    public User findByById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        User userToBeReturned = userService.findOneById(id);
        userToBeReturned.setPassword("");
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
        userToBeAdded.setPassword("");
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
            user.setPassword("");
            return user;
        } else {
            userService.saveWithoutPassEncoding(userToBeUpdated);
            user.setPassword("");
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
