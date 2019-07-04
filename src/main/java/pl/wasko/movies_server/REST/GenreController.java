package pl.wasko.movies_server.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.wasko.movies_server.model.Genre;
import pl.wasko.movies_server.service.GenreService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    //find all by last and first name with paging
    @GetMapping("")
    public List<Genre> findGenresByParamsPageable(
            @RequestParam(defaultValue = "") String genreName,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "genreName") String sortBy,
            HttpServletResponse response) {
        response.setContentType("application/json");
        return genreService.findByGenreName(genreName, of(page, size, Sort.by(sortBy)));
    }

    //find one by id
    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        return genreService.findOneById(id);
    }

    //update one by id
    @PutMapping("/{id}")
    public Genre updateGenreById(@RequestBody Genre genre, @PathVariable Long id) {
        //find Genre in db
        Genre GenreToBeUpdated = genreService.findOneById(id);
        //set filed values with received ones
        GenreToBeUpdated.setGenreName(genre.getGenreName());
        return genreService.save(GenreToBeUpdated);
    }

    //add one by id
    @PostMapping("")
    public Genre addGenre(@RequestBody Genre genre) {
        Genre GenreToBeAdded = new Genre();
        //set filed values with received ones
        GenreToBeAdded.setGenreName(genre.getGenreName());
        return genreService.save(GenreToBeAdded);
    }

    //find one by id
    @DeleteMapping("/{id}")
    public void deleteGenreById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json");
        genreService.delete(id);
        response.setStatus(204);
    }
}
