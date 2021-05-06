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

import com.bumptech.glide.Glide;

import dk.au.mad21spring.appproject.group21.Database.Team;

public class TeamActivity extends AppCompatActivity {

    //widgets
    private ImageView logo;
    private TextView name;
    private Button location;
    private Button players;
    private Button games;
    private Button back;

    //other variables
    private String teamName;

    //OBS MOVE TO CONSTANTS
    public static final String TEAM = "TEAM";

    //viewmodel
    private TeamViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Intent data = getIntent();
        teamName = data.getStringExtra(TEAM);

        viewModel = new ViewModelProvider(this, new TeamViewModelFactory(getApplication())).get(TeamViewModel.class);
        viewModel.getTeam(teamName).observe(this, new Observer<Team>() {
            @Override
            public void onChanged(Team team)
            {
                updateUI(team);
            }
        });

        logo = findViewById(R.id.imgLogo);
        name = findViewById(R.id.txtName);
        location = findViewById(R.id.btnLocation);
        players = findViewById(R.id.btnPlayers);
        games = findViewById(R.id.btnGames);
        back = findViewById(R.id.btnBack);

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    private void showLocation()
    {
        Intent intent = new Intent(this, LocationActivity.class);
        intent.putExtra(TEAM,teamName); //Bynavn
        startActivity(intent);
    }

    private void showPlayers()
    {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(TEAM,teamName);
        startActivity(intent);
    }

    private void showGames()
    {
        Intent intent = new Intent(this, LatestGamesActivity.class);
        intent.putExtra(TEAM,teamName);
        startActivity(intent);
    }

    private void back()
    {
        finish();
    }

    private void updateUI(Team team)
    {
        Glide.with(this).load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/"+team.getAbbreviation().toLowerCase()+".png").into(logo);
        name.setText(team.getFullname());
    }

}