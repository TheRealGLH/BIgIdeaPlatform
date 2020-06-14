package DatabaseConnector.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp timestamp;
    @NotNull
    String map;
    @ManyToMany
    @JoinTable(name = "game_player")
    Set<Player> players;
    @NotNull
    String victor;

    public Game(int id, Timestamp timestamp, String map, Set<Player> players, String victor) {
        this.id = id;
        this.timestamp = timestamp;
        this.map = map;
        this.players = players;
        this.victor = victor;
    }

    public Game(String map, Set<Player> players, String victor) {
        this.map = map;
        this.players = players;
        this.victor = victor;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getVictor() {
        return victor;
    }

    public void setVictor(String victor) {
        this.victor = victor;
    }
}
