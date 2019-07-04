package pl.wasko.movies_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.model.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    // This methods returns pageable result of db query
    //TODO create query to search by director and genre
    Page<Movie> findByTitleContainingIgnoreCaseAndDirector_LastNameContainingIgnoreCaseAndYearContaining(
            String title, String director_lastName, Integer year, Pageable pageable);
}
