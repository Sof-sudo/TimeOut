package dk.au.mad21spring.appproject.group21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        //playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);


    }
}