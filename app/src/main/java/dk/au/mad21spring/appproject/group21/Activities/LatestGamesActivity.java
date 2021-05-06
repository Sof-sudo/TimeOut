package dk.au.mad21spring.appproject.group21.Activities;

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
import dk.au.mad21spring.appproject.group21.Factories.LatestGamesViewModelFactory;
import dk.au.mad21spring.appproject.group21.R;
import dk.au.mad21spring.appproject.group21.Viewmodels.LatestGamesViewModel;

public class LatestGamesActivity extends AppCompatActivity {
    //public static final String INDEX = "index";

    // widgets
    private Button btnBack;
    private ImageView imgLogo;
    private TextView txtTeam;

    //ViewModel
    private LatestGamesViewModel latestGamesViewModel;

    //Other
    private String teamName;

    public static final String TEAM = "TEAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_games);

        Intent data = getIntent();
        teamName = data.getStringExtra(TEAM);

        //create ViewModel
        latestGamesViewModel = new ViewModelProvider(this, new LatestGamesViewModelFactory(getApplication())).get(LatestGamesViewModel.class);
        latestGamesViewModel.getTeam(teamName).observe(this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                updateUI(team);
            }
        });

setupUI();

    }

    private void setupUI() {
        btnBack = findViewById(R.id.btnBackStats);
        txtTeam = findViewById(R.id.txtNameStats);
        imgLogo = findViewById(R.id.imgLogoStats);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateUI(Team team) {
        Glide.with(this).load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/"+team.getAbbreviation()
                .toLowerCase()+".png").into(imgLogo);

        txtTeam.setText(team.getFullname());




    }
}