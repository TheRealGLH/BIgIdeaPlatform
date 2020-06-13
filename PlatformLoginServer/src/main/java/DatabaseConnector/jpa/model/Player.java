package DatabaseConnector.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

    private @Id String name;
    private String password;
    private Long score;

    public Player(String name, String password, Long score) {
        this.name = name;
        this.password = password;
        this.score = score;
    }

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.score = 0L;
    }

    public Player(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
