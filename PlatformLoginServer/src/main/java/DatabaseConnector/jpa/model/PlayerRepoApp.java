package DatabaseConnector.jpa.model;

import DatabaseConnector.jpa.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class PlayerRepoApp {


    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository) {
        return args -> {
            repository.save(new Player("Mr Test", "letmein123"));
            repository.save(new Player("Mrs Test", "letmein123"));

            for (Player player : repository.findAll()) {
                System.out.println("Player: " + player.getName());
            }
        };
    }

}
