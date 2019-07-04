package pl.wasko.movies_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wasko.movies_server.model.Director;

import java.util.List;

public interface DirectorRepository extends PagingAndSortingRepository<Director, Long> {
    // This methods return pageable result of db query
    Page<Director> findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String lastName, String firstName, Pageable pageable);
}
