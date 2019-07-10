package pl.wasko.movies_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wasko.movies_server.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findUsersByName(String name, Pageable pageable);
}
