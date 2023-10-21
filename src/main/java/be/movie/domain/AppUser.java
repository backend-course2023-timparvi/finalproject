package be.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "app_user")
public class AppUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
	
	@NotNull
    @NotBlank(message = "Please add a username")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	@Column(name = "username", nullable = false, unique = true)
    private String username;
	
	@NotNull
    @NotBlank(message = "Please add a password")
    @Column(name = "password", nullable = false)
    private String passwordHash;
    
	@NotNull
    @NotBlank
    @Column(name = "role", nullable = false)
    private String role;

	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppUser(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
