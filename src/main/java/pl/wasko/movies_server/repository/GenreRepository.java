package pl.wasko.movies_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.movies_server.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
