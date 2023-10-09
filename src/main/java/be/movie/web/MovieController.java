package be.movie.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import be.movie.domain.CategoryRepository;
import be.movie.domain.Movie;
import be.movie.domain.MovieRepository;


@Controller
public class MovieController {
	@Autowired
	private MovieRepository mrepository;
	
	@Autowired
	private CategoryRepository crepository;
	
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	 @GetMapping("/index")
	    public String showIndexPage(Model model) {
		 	logger.info("Accessing index page");
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
	public String save(Movie movie) {
		mrepository.save(movie);
		return "redirect:index";
	}
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteMovie(@PathVariable Long id) {
		mrepository.deleteById(id);
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
	public String updateMovie(@ModelAttribute Movie editedMovie) {
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
