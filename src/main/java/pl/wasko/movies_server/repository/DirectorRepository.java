package pl.wasko.movies_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wasko.movies_server.model.Director;

import java.util.List;

public interface DirectorRepository extends PagingAndSortingRepository<Director, Long> {
    // These methods return pageable result
    Page<Director> findAll(Pageable pageable);
    Page<Director> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
    Page<Director> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
}
