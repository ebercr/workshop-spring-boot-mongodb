package com.eberrabello.workshopmongo.services;

import com.eberrabello.workshopmongo.domain.Post;
import com.eberrabello.workshopmongo.repository.PostRepository;
import com.eberrabello.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repo.searchByTitle(text);
    }

    public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
        return repo.fullSearch(text, minDate, maxDate);
    }
}
