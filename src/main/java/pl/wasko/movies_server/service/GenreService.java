package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.Genre;
import pl.wasko.movies_server.repository.GenreRepository;

import java.util.Map;
import java.util.TreeMap;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Map<String, Object> findByGenreName(String genreName, PageRequest pageRequest) {
        Page<Genre> page = genreRepository.findByGenreNameContainingIgnoreCase(
                genreName, pageRequest);
        Map<String, Object> result = new TreeMap<String, Object>();
        result.put("content", page.getContent());
        result.put("totalElements", page.getTotalElements());
        result.put("totalPages", page.getTotalPages());
        return result;
    }

    public Genre findOneById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public Genre save(Genre director) {
        return genreRepository.save(director);
    }

    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
