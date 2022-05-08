package com.example.mybajnoksag;

public class Team {
    private String name;
    private String win;
    private String lose;
    private String draw;
    private String points;


    public Team() {}

    public Team(String name, String win, String lose, String draw, String points) {
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.points = points;
    }


    public String getName() {
        return name;
    }

    public String getWin() {
        return win;
    }

    public String getLose() {
        return lose;
    }

    public String getDraw() {
        return draw;
    }

    public String getPoints() {
        return points;
    }
}
