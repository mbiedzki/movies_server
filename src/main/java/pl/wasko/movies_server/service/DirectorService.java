package pl.wasko.movies_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wasko.movies_server.model.Director;
import pl.wasko.movies_server.repository.DirectorRepository;

import java.util.List;

@Service
public class DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    public List<Director> findByLastAndFirstName(String lastName, String firstName, PageRequest pageRequest) {
        Page<Director> directorsPage = directorRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(
                lastName, firstName, pageRequest);
        return directorsPage.getContent();
    }

    public Director findOneById(Long id) {
        return directorRepository.findById(id).get();
    }

    public Director save(Director director) {
        return directorRepository.save(director);
    }

    public void delete(Long id) {
        directorRepository.deleteById(id);
    }
}
