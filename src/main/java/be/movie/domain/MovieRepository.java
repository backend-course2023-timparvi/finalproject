package be.movie.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
	
	List<Movie> findByMovieName(String movieName);

}
