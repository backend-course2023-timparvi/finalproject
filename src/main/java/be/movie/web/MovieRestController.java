package be.movie.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import be.movie.domain.Movie;
import be.movie.domain.MovieRepository;
import be.movie.exception.ResourceNotFoundException;
import jakarta.validation.Valid;

@RestController
public class MovieRestController {
	
	@Autowired
	private MovieRepository mrepository;

	@GetMapping("/api/movies")
	public @ResponseBody List<Movie> showRestMovies() {
		return (List<Movie>) mrepository.findAll();
	}
	
	@GetMapping("/api/movie/{id}")
	public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long id) {
	return mrepository.findById(id);
	}
	
	@PostMapping("/api/movies")
	public @ResponseBody Movie addMovie(@Valid @RequestBody Movie movie) {
		return mrepository.save(movie);
	}
	
	@PutMapping("/api/movie/{id}")
	public @ResponseBody Movie updateMovie(@PathVariable("id") Long id, @RequestBody Movie movieDetails) {
	    Movie movie = mrepository.findById(id)
	    	.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
	    movie.setMovieName(movieDetails.getMovieName());
	    movie.setReleaseYear(movieDetails.getReleaseYear());
	    movie.setDirector(movieDetails.getDirector());
	    movie.setRating(movieDetails.getRating());
	    movie.setCategory(movieDetails.getCategory());

	    return mrepository.save(movie);
	}
	
	@DeleteMapping("/api/movie/{id}")
	public @ResponseBody Map<String, Boolean> deleteMovie(@PathVariable("id") Long id) {
	    Movie movie = mrepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

	    mrepository.delete(movie);

	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}
	
}
