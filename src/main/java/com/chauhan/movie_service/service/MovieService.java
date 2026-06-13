package com.chauhan.movie_service.service;

import com.chauhan.movie_service.model.Movie;
import com.chauhan.movie_service.repo.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
public class MovieService {


    @Autowired
    private MovieRepository movieRepository;

    //create read update and delete

    public Movie create(Movie movie) {
        if (movie == null) {
            throw new RuntimeException("Movie is null");
        }
        return movieRepository.save(movie);

    }

    public Movie read(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }


    public void update(Long id, Movie update) {
        if (update == null || id == null) {
            throw new RuntimeException("Invalid Movie");
        }

        //check if exists
        if (movieRepository.existsById(id)) {
            Movie movie = movieRepository.getReferenceById(id);
            movie.setName(update.getName());
            movie.setDirector(update.getDirector());
            movie.setActors(update.getActors());
            movieRepository.save(movie);

        } else {
            throw new RuntimeException("Movie not found");
        }

    }
      public void delete(Long id)
        {
            if(movieRepository.existsById(id))
            {
                movieRepository.deleteById(id);
            }
            else {
                throw new RuntimeException("Movie not found");
            }

        }



}
