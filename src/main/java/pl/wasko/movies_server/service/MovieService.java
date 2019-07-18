package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.Movie;
import pl.wasko.movies_server.repository.MovieRepository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Map findByParams(String title, String directorLastName, String year, PageRequest pageRequest) {

        Page<Movie> page = movieRepository.findByTitleContainingIgnoreCaseAndDirector_LastNameContainingIgnoreCaseAndYearContainingIgnoreCase(
                title, directorLastName, year, pageRequest);
        Map result = new TreeMap();
        result.put("content", page.getContent());
        result.put("totalElements", page.getTotalElements());
        result.put("totalPages", page.getTotalPages());
        return result;
    }

    public Movie findOneById(Long id) {
        return movieRepository.findById(id).get();
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
