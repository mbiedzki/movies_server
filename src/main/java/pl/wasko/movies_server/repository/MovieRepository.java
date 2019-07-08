package pl.wasko.movies_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.model.Genre;
import pl.wasko.movies_server.model.Movie;

import java.util.Set;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    // This methods returns pageable result of db query
    Page<Movie> findByTitleContainingIgnoreCaseAndDirector_LastNameContainingIgnoreCaseAndYearContainingIgnoreCase(
            String title, String director_lastName, String year, Pageable pageable);
}
