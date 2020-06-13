package DatabaseConnector.jpa;

import DatabaseConnector.jpa.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,String> {
}
