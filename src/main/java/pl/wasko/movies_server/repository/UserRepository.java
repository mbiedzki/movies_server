package pl.wasko.movies_server.repository;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wasko.movies_server.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByName(String name);
    Page<User> findUsersByNameContainingIgnoreCase(String name, Pageable pageable);
}
