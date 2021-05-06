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

public class PlayerActivity extends AppCompatActivity {

public static final String INDEX = "index";

    // widgets
    private Button btnBack;
    private ImageView imgLogo;
    private TextView txtPlayers;
    private TextView txtTeam;

    private String teamName;

    //ViewModel
    private PlayerViewModel playerViewModel;
    private Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);

        Intent data = getIntent();
        String teamName = data.getStringExtra(INDEX);

        // Oprettelse af ViewModel
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        playerViewModel.getAll().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                //players.get(teamName);
                updateUI();
            }
        });



        btnBack = findViewById(R.id.BtnBackPlayer);
        txtPlayers = findViewById(R.id.txtPlayers);
        txtTeam = findViewById(R.id.txtNamePlayer);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void updateUI() {

      //  String base = "https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/"+TeamList.get(position).getAbbreviation().toLowerCase()+".png").into(holder.imgTeamLogo);
//        Glide.with(holder.imgTeamLogo.getContext())
//                .load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/"+TeamList.get(position)
//                        .getAbbreviation().toLowerCase()+".png").into(holder.imgTeamLogo);
//        holder.txtTeamName.setText(TeamList.get(position).getFullname());

        txtTeam.setText("Team");

        // hvordan får vi lige flere spillere ind??
        txtPlayers.setText(player.getFirstname()+player.getLastname()+" Position: " +player.getPosition() );



    }


}