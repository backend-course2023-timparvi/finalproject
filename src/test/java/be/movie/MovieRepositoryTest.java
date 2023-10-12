package be.movie;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import be.movie.domain.Category;
import be.movie.domain.CategoryRepository;
import be.movie.domain.Movie;
import be.movie.domain.MovieRepository;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
public class MovieRepositoryTest {
	
	@Autowired
	private MovieRepository mrepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	public void WhereMovie() {
		assertThat(mrepository.count()).isEqualTo(0);
	}
	
	@Test
	public void addAndCheckNewMovie() {
		Category category = new Category("Sci-fi");
		crepository.save(category);
		assertThat(crepository.count()).isEqualTo(1);
		Movie movie = new Movie("Inception", 2010, "Christopher Nolan", 8.8, category);
		mrepository.save(movie);
		assertThat(mrepository.count()).isEqualTo(1);
	}
	
	@Test
	public void deleteMovie() {
		 // Luodaan elokuva
	    Category category = new Category("Drama");
	    crepository.save(category);
	    Movie movie = new Movie("12 Angry Men", 1957, "Sidney Lumet", 9.0, category);
	    mrepository.save(movie);
	    
	    // Poisto
	    mrepository.delete(movie);

	    // Varmistetaan, että poisto onnistuu
	    List<Movie> newMovies = mrepository.findByMovieName("Inception");
	    assertThat(newMovies).isEmpty(); // using isEmpty() is more intuitive than hasSize(0)
	}
	
	@Test
	public void editMovie() {
		// Luodaan elokuva
		Category category = new Category("Action");
	    crepository.save(category);
	    Movie movie = new Movie("Mad Max: Fury Road", 2015, "George Harrison", 7.1, category);
	    mrepository.save(movie);
	    
	    // Vahvistetaan alkuperäiset tiedot
	    List<Movie> movies = mrepository.findByMovieName("Mad Max: Fury Road");
	    assertThat(movies).hasSize(1);
	    Movie fetchedMovie = movies.get(0);
	    assertThat(fetchedMovie.getDirector()).isEqualTo("George Harrison");
	    
	    // Muutetaan tiedot
	    fetchedMovie.setDirector("George Miller");
	    fetchedMovie.setRating(8.1);
	    mrepository.save(fetchedMovie);
	    
	    // Vahvistetaan muutokset
	    movies = mrepository.findByMovieName("Mad Max: Fury Road");
	    assertThat(movies).hasSize(1);
	    Movie editedMovie = movies.get(0);
	    assertThat(editedMovie.getDirector()).isEqualTo("George Miller");
	    assertThat(editedMovie.getRating()).isEqualTo(8.1);
	    
	}
	
	@Test
	public void testFindByDirector () {
		// Luodaan elokuvat
		Category category = new Category("Sci-fi");
	    crepository.save(category);

	    Movie movie1 = new Movie("Inception", 2010, "Christopher Nolan", 8.8, category);
	    Movie movie2 = new Movie("The Dark Knight", 2008, "Christopher Nolan", 9.0, category);
	    mrepository.save(movie1);
	    mrepository.save(movie2);

	    List<Movie> moviesByNolan = mrepository.findByDirector("Christopher Nolan");
	    
	    // Varmistetaan FindByDirector palauttaa tiedot
	    assertThat(moviesByNolan).hasSize(2);

	    // Vielä vahvistus, että molemmat ovat Nolanin
	    assertThat(moviesByNolan).allMatch(movie -> "Christopher Nolan".equals(movie.getDirector()));
	}
}