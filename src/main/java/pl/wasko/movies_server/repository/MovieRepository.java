package pl.wasko.movies_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.model.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    // This methods returns pageable result of db query
    //TODO create query to search by director and genre
    Page<Movie> findByTitleContainingIgnoreCaseAndDirectorContainingIgnoreCase(String title, Director director, Pageable pageable);
}
