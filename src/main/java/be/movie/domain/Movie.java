package be.movie.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;


@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Please add movie name")
	private String movieName;
	
	@NotNull(message = "Please add a year")
	@Min(value = 1888, message = "Year must be greater than or equal to 1888")
	@Max(value = 2050, message = "Year must be less than or equal to 2050")
	private Integer releaseYear;
	
	@NotEmpty(message = "Please add director name")
	private String director;
	
	@DecimalMin(value = "0.1", message = "Rating cannot be zero or less")
	@DecimalMax(value = "10.0", message = "Rating cannot be greater than 10")
	private double rating;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	
	public Movie() {

	}

	public Movie(String movieName, int releaseYear, String director, double rating, Category category) {
		super();
		this.movieName = movieName;
		this.releaseYear = releaseYear;
		this.director = director;
		this.rating = rating;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}