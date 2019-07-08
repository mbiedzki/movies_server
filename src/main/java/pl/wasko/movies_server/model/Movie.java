package pl.wasko.movies_server.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Transactional
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @ManyToOne
    private Director director;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genres;
    @NotBlank
    private String year;
    private String description;

    public Movie(String title, Director director, Set<Genre> genres, String year, String description) {
        this.title = title;
        this.director = director;
        this.genres = genres;
        this.year = year;
        this.description = description;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director=" + director +
                ", genres=" + genres +
                ", year='" + year + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
