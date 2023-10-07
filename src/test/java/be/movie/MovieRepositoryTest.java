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
	public void DeleteMovie() {
		List<Movie> movies = mrepository.findByMovieName("Inception");
		Movie movie = movies.get(0);
		mrepository.delete(movie);
		List<Movie> newMovies = mrepository.findByMovieName("Inception");
		assertThat(newMovies).hasSize(0);
	}
	
}