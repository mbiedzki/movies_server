package pl.wasko.movies_server.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Transactional
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
