package com.eberrabello.workshopmongo.config;

import com.eberrabello.workshopmongo.domain.Post;
import com.eberrabello.workshopmongo.domain.User;
import com.eberrabello.workshopmongo.dto.AuthorDTO;
import com.eberrabello.workshopmongo.dto.CommentDTO;
import com.eberrabello.workshopmongo.repository.PostRepository;
import com.eberrabello.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.parse("2024-03-21T10:12:30.58Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, Instant.parse("2024-03-23T12:18:45.21Z"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.parse("2024-03-21T11:18:59.35Z"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", Instant.parse("2024-03-22T08:27:32.09Z"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Instant.parse("2024-03-23T14:55:12.41Z"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
