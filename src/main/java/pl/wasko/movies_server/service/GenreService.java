package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.Genre;
import pl.wasko.movies_server.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findByGenreName(String genreName, PageRequest pageRequest) {
        Page<Genre> genresPage = genreRepository.findByGenreNameContainingIgnoreCase(
                genreName, pageRequest);
        return genresPage.getContent();
    }

    public Genre findOneById(Long id) {
        return genreRepository.findById(id).get();
    }

    public Genre save(Genre director) {
        return genreRepository.save(director);
    }

    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
