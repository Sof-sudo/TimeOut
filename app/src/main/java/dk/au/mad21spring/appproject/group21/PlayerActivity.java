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

public class PlayerActivity extends AppCompatActivity {

public static final String INDEX = "index";

    // widgets
    private Button btnBack;
    private ImageView imgLogo;
    private TextView txtPlayers;
    private TextView txtTeam;

    //ViewModel
    private PlayerViewModel playerViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);

        Intent data = getIntent();
        int uid = data.getIntExtra(INDEX, 0);

        // Oprettelse af ViewModel
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
//        playerViewModel.getAll().observe(this, new Observer<List<Players>>() {
//            @Override
//            public void onChanged(List<Players> players) {
//                players.get(uid);
//                updateUI();
//            }
//        });



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

//        Glide.with(holder.imgTeamLogo.getContext())
//                .load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/"+TeamList.get(position)
//                        .getAbbreviation().toLowerCase()+".png").into(holder.imgTeamLogo);
//        holder.txtTeamName.setText(TeamList.get(position).getFullname());



    }


}