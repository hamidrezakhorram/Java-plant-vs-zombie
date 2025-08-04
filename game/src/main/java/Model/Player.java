package Model;

import Model.plants.Plant;

import java.util.ArrayList;

public class Player {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String username;
    private ArrayList<Plant> availablePlants;
    private String currentLevel;
    private Integer score;
    private Integer win;
    private Integer loss;

    public Integer getLoss() {
        return loss;
    }

    public void setLoss(Integer loss) {
        this.loss = loss;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
       this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Plant> getAvailablePlants() {
        return availablePlants;
    }

    public void setAvailablePlants(ArrayList<Plant> availablePlants) {
        this.availablePlants = availablePlants;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }
}
