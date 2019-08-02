package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.Movie;
import pl.wasko.movies_server.repository.MovieRepository;

import java.util.Map;
import java.util.TreeMap;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Map<String, Object> findByParams(String title, String directorLastName, String year, PageRequest pageRequest) {

        Page<Movie> page = movieRepository.findByTitleContainingIgnoreCaseAndDirector_LastNameContainingIgnoreCaseAndYearContainingIgnoreCase(
                title, directorLastName, year, pageRequest);
        Map<String, Object> result = new TreeMap<>();
        result.put("content", page.getContent());
        result.put("totalElements", page.getTotalElements());
        result.put("totalPages", page.getTotalPages());
        return result;
    }

    //depending on request we use 2 different methods to find movies, this one uses more complicated db query and is only used if user queries by genre
    public Map<String, Object> findByParamsWithGenres(String title, String directorLastName, String year, String genreName, PageRequest pageRequest) {

        Page<Movie> page = movieRepository.findByGenresAndParams(
                title, directorLastName, year, genreName, pageRequest);
        Map<String, Object> result = new TreeMap<>();
        result.put("content", page.getContent());
        result.put("totalElements", page.getTotalElements());
        result.put("totalPages", page.getTotalPages());
        return result;
    }

    public Movie findOneById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
