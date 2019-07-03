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

    public List<Director> findAll(PageRequest pageRequest) {
        Page<Director> directorsPage = directorRepository.findAll(pageRequest);
        return directorsPage.getContent();
    }

    public List<Director> findByLastName(String lastName, PageRequest pageRequest) {
        Page<Director> directorsPage = directorRepository.findByLastNameContainingIgnoreCase(lastName, pageRequest);
        return directorsPage.getContent();
    }
}
