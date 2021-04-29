package dk.au.mad21spring.appproject.group21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dk.au.mad21spring.appproject.group21.Database.Teams;

public class TeamActivity extends AppCompatActivity {

    //widgets
    private ImageView logo;
    private TextView name;
    private Button location;
    private Button players;
    private Button games;

    //other variables
    private String team;

    //OBS MOVE TO CONSTANTS
    public static final String TEAM = "Team";

    //viewmodel
    private TeamViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Intent data = getIntent();
        team = data.getStringExtra(TEAM);

        viewModel = new ViewModelProvider(this).get(TeamViewModel.class);
//        viewModel.getTeam(team).observe(this, new Observer<Team>() {
//            @Override
//            public void onChanged(Team team)
//            {
//                updateUI(team);
//            }
//        });

        logo = findViewById(R.id.imgLogo);
        name = findViewById(R.id.txtName);
        location = findViewById(R.id.btnLocation);
        players = findViewById(R.id.btnPlayers);
        games = findViewById(R.id.btnGames);


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocation();
            }
        });

        players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlayers();
            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGames();
            }
        });
    }

    private void showLocation()
    {}

    private void showPlayers()
    {}

    private void showGames()
    {}

    private void updateUI(Teams team)
    {}

}