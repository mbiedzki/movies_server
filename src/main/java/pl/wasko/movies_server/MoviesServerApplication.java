package pl.wasko.movies_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesServerApplication.class, args);
		System.out.println("app works");
	}

}
