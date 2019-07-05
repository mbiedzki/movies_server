package pl.wasko.movies_server.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.model.Genre;
import pl.wasko.movies_server.model.Movie;
import pl.wasko.movies_server.service.DirectorService;
import pl.wasko.movies_server.service.MovieService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.domain.PageRequest.of;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private DirectorService directorService;
    //find all by last and first name with paging
    @GetMapping("")
    public List<Movie> findMovieByParamsPageable(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String directorLastName,
            @RequestParam(defaultValue = "") String year,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "title") String sortBy,
            HttpServletResponse response) {
        response.setContentType("application/json");
        return movieService.findByParams(title, directorLastName, year, of(page, size, Sort.by(sortBy)));
    }

    //find one by id
    @GetMapping("/{id}")
    public Movie getmovieById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        return movieService.findOneById(id);
    }

    //update one by id
    @PutMapping("/{id}")
    public Movie updateMovieById(@RequestBody Movie movie, @PathVariable Long id) {
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
    public Movie addMovie(@RequestBody Movie movie) {
        Movie movieToBeAdded = new Movie();

        //set new movie values with received ones
        movieToBeAdded.setTitle(movie.getTitle());
        movieToBeAdded.setDirector(movie.getDirector());
        movieToBeAdded.setGenres(movie.getGenres());
        movieToBeAdded.setYear(movie.getYear());
        movieToBeAdded.setDescription(movie.getDescription());

        return movieService.save(movieToBeAdded);
    }

    //find one by id
    @DeleteMapping("/{id}")
    public void deletemovieById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        movieService.delete(id);
        response.setStatus(204);
    }
}
