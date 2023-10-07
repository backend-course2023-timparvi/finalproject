package be.movie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.movie.web.MovieController;

@SpringBootTest
class ProjectApplicationTests {
	
	@Autowired
	private MovieController controller;
	

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
