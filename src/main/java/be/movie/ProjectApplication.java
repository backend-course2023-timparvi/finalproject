package be.movie;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import be.movie.domain.AppUser;
import be.movie.domain.AppUserRepository;
import be.movie.domain.MovieRepository;



@SpringBootApplication
public class ProjectApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initUsers(AppUserRepository urepository) {
	    return (args) -> {
	 	
	    	/*if (urepository.findByUsername("user") == null) {
	    	AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
	    	urepository.save(user1);
	    	}
	    	if (urepository.findByUsername("admin") == null) {
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user2);
	    	}*/
	};
}
}