package be.movie.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.movie.domain.CategoryRepository;
import be.movie.domain.Movie;
import be.movie.domain.MovieRepository;
import jakarta.validation.Valid;


@Controller
public class MovieController {
	
	 private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private MovieRepository mrepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	 @GetMapping(value = {"/index", "/"})
	    public String showIndexPage(Model model) {
		 	model.addAttribute("movies", mrepository.findAll());
	        return "index";
	    }
	 @GetMapping("/addmovie")
	 @PreAuthorize("hasAuthority('ADMIN')")
	    public String addMovie(Model model){
	    	model.addAttribute("movie", new Movie());
	    	model.addAttribute("categories", crepository.findAll());
	        return "addmovie";
	    }     
	@PostMapping("save")
	public String save(@Valid Movie movie, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("categories", crepository.findAll());
			return "addmovie";
		}
		mrepository.save(movie);
		return "redirect:index";
	}
	
	@PostMapping("/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteMovie(@RequestParam Long id) {
		try {
	        logger.info("Deleting movie with ID: {}", id);
	        mrepository.deleteById(id);
	    } catch (EmptyResultDataAccessException e) {
	        logger.error("Error deleting movie with ID: {}. Movie not found.", id);
	    }
	    return "redirect:/index";
	}
	
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editMovie(@PathVariable Long id, Model model) {
		Optional<Movie> movieOptimal = mrepository.findById(id);
		model.addAttribute("movie", movieOptimal.get());
		model.addAttribute("categories", crepository.findAll());
		return "editmovie";
	}
	
	@PostMapping("/update")
	public String updateMovie(@Valid @ModelAttribute Movie editedMovie, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("categories", crepository.findAll());
			return "editmovie";
		}
		mrepository.save(editedMovie);
		return "redirect:/index";
	}
	
	@GetMapping("/director/{directorName}")
	public String getMoviesByDirector(@PathVariable String directorName, Model model) {
	    List<Movie> moviesByDirector = mrepository.findByDirector(directorName);
	    model.addAttribute("movies", moviesByDirector);
	    model.addAttribute("director", directorName);
	    return "directormovies";
	}
}
