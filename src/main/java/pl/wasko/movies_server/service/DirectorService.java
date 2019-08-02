package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.repository.DirectorRepository;

import java.util.Map;
import java.util.TreeMap;

@Service
public class DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    public Map<String, Object> findByLastAndFirstName(String lastName, String firstName, PageRequest pageRequest) {
        Page<Director> page = directorRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(
                lastName, firstName, pageRequest);
        Map<String, Object> result = new TreeMap<String, Object>();
        result.put("content", page.getContent());
        result.put("totalElements", page.getTotalElements());
        result.put("totalPages", page.getTotalPages());
        return result;
    }

    public Director findOneById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    public Director save(Director director) {
        return directorRepository.save(director);
    }

    public void delete(Long id) {
        directorRepository.deleteById(id);
    }
}
