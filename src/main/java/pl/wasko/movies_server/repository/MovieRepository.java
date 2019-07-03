package pl.wasko.movies_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.movies_server.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
