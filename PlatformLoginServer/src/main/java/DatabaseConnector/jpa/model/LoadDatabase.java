package DatabaseConnector.jpa.model;

import DatabaseConnector.jpa.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository) {
        return args -> {
            System.out.println("Filling db: ");
            System.out.println(repository.save(new Player("Mr Test", "letmein123")));
            System.out.println(repository.save(new Player("Mrs Test", "letmein123")));

            for (Player player : repository.findAll()) {
                System.out.println("Player: " + player.getName());
            }

        };
    }

}
