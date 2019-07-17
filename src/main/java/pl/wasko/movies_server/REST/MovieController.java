package pl.wasko.movies_server.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.wasko.movies_server.model.Movie;
import pl.wasko.movies_server.service.DirectorService;
import pl.wasko.movies_server.service.MovieService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    //find all by last and first name with paging
    @GetMapping("")
    public Page<Movie> findByParamsPageable(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String directorLastName,
            @RequestParam(defaultValue = "") String year,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            HttpServletResponse response) {
        response.setContentType("application/json");
        if (sortOrder.equals("asc")) {
            return movieService.findByParams(title, directorLastName, year, of(page, size, Sort.by(sortBy).ascending()));
        } else {
            return movieService.findByParams(title, directorLastName, year, of(page, size, Sort.by(sortBy).descending()));
        }
    }

    //find one by id
    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        return movieService.findOneById(id);
    }

    //update one by id
    @PutMapping("/{id}")
    public Movie update(@RequestBody Movie movie, @PathVariable Long id) {
        //find movie in db movie movieToBeUpdated = movieService.findOneById(id);
        Movie movieToBeUpdated = movieService.findOneById(id);

        //set new movie values with received ones
        movieToBeUpdated.setTitle(movie.getTitle());
        movieToBeUpdated.setDirector(movie.getDirector());
        movieToBeUpdated.setGenres(movie.getGenres());
        movieToBeUpdated.setYear(movie.getYear());
        movieToBeUpdated.setDescription(movie.getDescription());

        return movieService.save(movieToBeUpdated);
    }

    //add one by id
    @PostMapping("")
    public Movie add(@RequestBody Movie movie) {
        Movie movieToBeAdded = new Movie();

        //set new movie values with received ones
        movieToBeAdded.setTitle(movie.getTitle());
        movieToBeAdded.setDirector(movie.getDirector());
        movieToBeAdded.setGenres(movie.getGenres());
        movieToBeAdded.setYear(movie.getYear());
        movieToBeAdded.setDescription(movie.getDescription());

        return movieService.save(movieToBeAdded);
    }

    //delete one by id
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        movieService.delete(id);
        response.setStatus(204);
    }
}
