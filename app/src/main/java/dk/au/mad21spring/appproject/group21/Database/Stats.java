package dk.au.mad21spring.appproject.group21.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Stats {

    @PrimaryKey
    private int statsID;
    private int ast;
    private int blk;
    private int dreb;
    private double fg3_pct;
    private int fg3a;
    private int fg3m;
    private double fg_pct;
    private int fga;
    private int fgm;
    private double ft_pct;
    private int fta;
    private int ftm;
    private Games game;
    private String min;
    private int oreb;
    private int pf;
    private Player player;
    private int pts;
    private int reb;
    private int stl;
    private Teams team;
    private int turnover;

    public Stats(){
        //instantier de stats som vi vil have med
    }

}
