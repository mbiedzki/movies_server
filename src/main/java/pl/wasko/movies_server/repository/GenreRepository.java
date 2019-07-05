package pl.wasko.movies_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wasko.movies_server.model.Genre;

import java.util.Set;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    // This methods returns pageable result of db query
    Page<Genre> findByGenreNameContainingIgnoreCase(String genreName, Pageable pageable);
    Set<Genre> findByGenreNameContainingIgnoreCase(String genreName);
}
