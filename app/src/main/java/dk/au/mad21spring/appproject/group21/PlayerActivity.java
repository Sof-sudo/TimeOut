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

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Database.Team;

public class PlayerActivity extends AppCompatActivity {

public static final String INDEX = "index";

    // widgets
    private Button btnBack;
    private ImageView imgLogo;
    private TextView txtPlayers;
    private TextView txtTeam;

    //ViewModel
    private PlayerViewModel playerViewModel;
    private Player player;

    //other variables
    private String teamName;

    //OBS MOVE TO CONSTANTS
    public static final String TEAM = "TEAM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);

        Intent data = getIntent();
        teamName = data.getStringExtra(TEAM);

        // Oprettelse af ViewModel
        playerViewModel = new ViewModelProvider(this, new PlayerViewModelFactory(getApplication())).get(PlayerViewModel.class);
        playerViewModel.getTeam(teamName).observe(this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                updateUI(team);
            }
        });

        setupUI();
    }

    private void setupUI() {
        btnBack = findViewById(R.id.BtnBackPlayer);
        txtPlayers = findViewById(R.id.txtPlayers);
        txtTeam = findViewById(R.id.txtNamePlayer);
        imgLogo = findViewById(R.id.imgLogoPlayer);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateUI(Team team)
    {
        Glide.with(this).load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/"+team.getAbbreviation()
                .toLowerCase()+".png").into(imgLogo);
        txtTeam.setText(team.getFullname());
        txtPlayers.setText("Players");
    }
}