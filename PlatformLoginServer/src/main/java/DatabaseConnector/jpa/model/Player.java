package DatabaseConnector.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

    private @Id String name;
    private String passWord;
    private Long score;

    public Player(String name, String passWord, Long score) {
        this.name = name;
        this.passWord = passWord;
        this.score = score;
    }

    public Player(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
        this.score = 0L;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
