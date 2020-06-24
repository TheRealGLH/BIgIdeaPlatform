package DatabaseConnector.jpa.model;

import DatabaseConnector.LoginDatabaseJDBC;
import DatabaseConnector.jpa.PlayerRepository;
import PlatformGameShared.PropertiesLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {

    private static final String secret = PropertiesLoader.getPropValues("jdbc.secret", "jdbc.properties");

    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository) {
        return args -> {
            System.out.println("Filling db with test users: ");
            System.out.println(repository.save(new Player("Mr Test", LoginDatabaseJDBC.encrypt("letmein123",secret))));
            System.out.println(repository.save(new Player("Mrs Test", LoginDatabaseJDBC.encrypt("letmein123",secret))));

            for (Player player : repository.findAll()) {
                System.out.println("Player: " + player.getName());
            }

        };
    }

}
