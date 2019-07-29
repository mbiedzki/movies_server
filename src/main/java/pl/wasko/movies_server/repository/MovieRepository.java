package pl.wasko.movies_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.model.Genre;
import pl.wasko.movies_server.model.Movie;

import java.util.List;
import java.util.Set;
@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    // This methods returns pageable result of db query
    Page<Movie> findByTitleContainingIgnoreCaseAndDirector_LastNameContainingIgnoreCaseAndYearContainingIgnoreCase(
            String title, String director_lastName, String year, Pageable pageable);
    //test
    @Query( value = "select * from movies m " +
            "join movies_genres mg on m.id = mg.movie_id " +
            "join genres g on g.id = mg.genres_id " +
            "join directors d on d.id = m.director_id " +
            "where lower(m.title) like %:title% " +
            "and lower(d.last_name) like %:directorLastName% " +
            "and lower(m.year) like %:year% " +
            "and lower(g.genre_name) like %:genreName% "
            , nativeQuery = true)
    Page<Movie> findByGenresAndParams(
            @Param("title") String title,
            @Param("directorLastName") String directorLastName,
            @Param("year") String year,
            @Param("genreName") String genreName,
            Pageable pageable);

}