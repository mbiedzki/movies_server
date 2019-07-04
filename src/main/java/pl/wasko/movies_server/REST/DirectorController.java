package pl.wasko.movies_server.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.service.DirectorService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    //find all with paging
    @GetMapping("")
    public List<Director> getDirectorsPageable(@RequestParam Integer page,
                                               @RequestParam Integer size, HttpServletResponse response) {
        response.setContentType("application/json");
        return directorService.findAllPageable(of(page, size));
    }

    //find all by last name with paging
    @GetMapping("/findbylastname")
    public List<Director> findDirectorsByLastNamePageable(@RequestParam String lastName, @RequestParam Integer page,
                                               @RequestParam Integer size, HttpServletResponse response) {
        response.setContentType("application/json");
        return directorService.findByLastName(lastName, of(page, size));
    }

    //find all by first name with paging
    @GetMapping("/findbyfirstname")
    public List<Director> findDirectorsByFirstNamePageable(@RequestParam String firstName, @RequestParam Integer page,
                                                          @RequestParam Integer size, HttpServletResponse response) {
        response.setContentType("application/json");
        return directorService.findByFirstName(firstName, of(page, size));
    }

    //find one by id
    @GetMapping("/{id}")
    public Director getDirectorById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        return directorService.findOneById(id);
    }

    //update one by id
    @PutMapping("/{id}")
    public Director updateDirectorById(@RequestBody Director director, @PathVariable Long id) {
        //find director in db
        Director directorToBeUpdated = directorService.findOneById(id);
        //set filed values with received ones
        directorToBeUpdated.setFirstName(director.getFirstName());
        directorToBeUpdated.setLastName(director.getLastName());
        return directorService.save(directorToBeUpdated);
    }

    //add one by id
    @PostMapping("/")
    public Director addDirector(@RequestBody Director director) {
        Director directorToBeAdded = new Director();
        //set filed values with received ones
        directorToBeAdded.setFirstName(director.getFirstName());
        directorToBeAdded.setLastName(director.getLastName());
        return directorService.save(directorToBeAdded);
    }
}
