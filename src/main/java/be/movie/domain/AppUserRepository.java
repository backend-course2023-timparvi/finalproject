package be.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);
	
}
