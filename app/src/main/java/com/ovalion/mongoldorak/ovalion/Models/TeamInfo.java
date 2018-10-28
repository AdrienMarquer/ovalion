package com.ovalion.mongoldorak.ovalion.Models;

public class TeamInfo {
    private Competitor competitor;
    private int matches_played;
    private int matches_won;
    private int matches_drawn;
    private int matches_lost;
    private int points_scored;
    private int points_conceded;

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    public int getMatches_played() {
        return matches_played;
    }

    public void setMatches_played(int matches_played) {
        this.matches_played = matches_played;
    }

    public int getMatches_won() {
        return matches_won;
    }

    public void setMatches_won(int matches_won) {
        this.matches_won = matches_won;
    }

    public int getMatches_drawn() {
        return matches_drawn;
    }

    public void setMatches_drawn(int matches_drawn) {
        this.matches_drawn = matches_drawn;
    }

    public int getMatches_lost() {
        return matches_lost;
    }

    public void setMatches_lost(int matches_lost) {
        this.matches_lost = matches_lost;
    }

    public int getPoints_scored() {
        return points_scored;
    }

    public void setPoints_scored(int points_scored) {
        this.points_scored = points_scored;
    }

    public int getPoints_conceded() {
        return points_conceded;
    }

    public void setPoints_conceded(int points_conceded) {
        this.points_conceded = points_conceded;
    }
}
