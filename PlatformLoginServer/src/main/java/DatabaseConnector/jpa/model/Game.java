package DatabaseConnector.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp timestamp;
    @NotNull
    String map;
    @ManyToMany
    @JoinTable(name = "game_player")
    Set<Player> player;
    @NotNull
    String victor;

    public Game(int id, Timestamp timestamp, String map, Set<Player> player, String victor) {
        this.id = id;
        this.timestamp = timestamp;
        this.map = map;
        this.player = player;
        this.victor = victor;
    }

    public Game(String map, Set<Player> player, String victor) {
        this.map = map;
        this.player = player;
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

    public Set<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Set<Player> player) {
        this.player = player;
    }

    public String getVictor() {
        return victor;
    }

    public void setVictor(String victor) {
        this.victor = victor;
    }
}
