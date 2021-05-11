package dk.au.mad21spring.appproject.group21.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Factories.PlayerViewModelFactory;
import dk.au.mad21spring.appproject.group21.Location_API.Coord;
import dk.au.mad21spring.appproject.group21.R;
import dk.au.mad21spring.appproject.group21.Viewmodels.PlayerViewModel;
import dk.au.mad21spring.appproject.group21.VolleyCallback;

public class PlayerActivity extends AppCompatActivity {

public static final String INDEX = "index";

    // widgets
    private Button btnBack, btnSearch;
    private ImageView imgLogo;
    private TextView txtPlayers;
    private TextView txtTeam;
    private EditText edtSearchPlayer;

    //ViewModel
    private PlayerViewModel playerViewModel;
    private Player player;

    //other variables
    private String teamName;
    private String name;

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
        //txtPlayers = findViewById(R.id.txtPlayers);
        txtTeam = findViewById(R.id.txtNamePlayer);
        imgLogo = findViewById(R.id.imgLogoPlayer);
        btnSearch = findViewById(R.id.btnSearchPlayer);
        edtSearchPlayer = findViewById(R.id.edtSearchPlayer);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        VolleyCallback callback = new VolleyCallback() {
            @Override
            public void onSucces(Coord result) {

            }

            @Override
            public void onError(Coord result) {

            }

            @Override
            public void onSuccesPlayer(Player result) {
                name = result.getFirstname();

            }

            @Override
            public void onErrorPlayer(Player result) {

            }
        };

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerViewModel.getPlayer(edtSearchPlayer.getText().toString(), callback);
                txtPlayers.setText(name);
            }
        });
    }

    private void updateUI(Team team)
    {
        Glide.with(this).load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/"+team.getAbbreviation()
                .toLowerCase()+".png").into(imgLogo);
        txtTeam.setText(team.getFullname());
    }


}