package pl.wasko.movies_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.wasko.movies_server.model.User;
import pl.wasko.movies_server.service.UserService;

@SpringBootApplication()
public class MoviesServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesServerApplication.class, args);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("movies app works");
    }

}

//TODO: this code replaces code above for implementation on production server
/*@SpringBootApplication
public class SpringBootApp extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootApp.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
}*/
