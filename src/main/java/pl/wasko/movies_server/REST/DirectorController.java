package pl.wasko.movies_server.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.service.DirectorService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.PageRequest.of;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    //find all by last and first name with paging
    @GetMapping("")
    public Map findByParamsPageable(
            @RequestParam(defaultValue = "") String lastName,
            @RequestParam(defaultValue = "") String firstName,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "lastName") String sortBy,
            HttpServletResponse response) {
        response.setContentType("application/json");
        return directorService.findByLastAndFirstName(lastName, firstName, of(page, size, Sort.by(sortBy)));
    }

    //find one by id
    @GetMapping("/{id}")
    public Director findById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        return directorService.findOneById(id);
    }

    //update one by id
    @PutMapping("/{id}")
    public Director update(@RequestBody Director director, @PathVariable Long id) {
        //find director in db
        Director directorToBeUpdated = directorService.findOneById(id);
        //set filed values with received ones
        directorToBeUpdated.setFirstName(director.getFirstName());
        directorToBeUpdated.setLastName(director.getLastName());
        return directorService.save(directorToBeUpdated);
    }

    //add one by id
    @PostMapping("")
    public Director add(@RequestBody Director director) {
        Director directorToBeAdded = new Director();
        //set filed values with received ones
        directorToBeAdded.setFirstName(director.getFirstName());
        directorToBeAdded.setLastName(director.getLastName());
        return directorService.save(directorToBeAdded);
    }

    //delete one by id
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        directorService.delete(id);
        response.setStatus(204);
    }
}
