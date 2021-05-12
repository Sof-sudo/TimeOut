package dk.au.mad21spring.appproject.group21.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import dk.au.mad21spring.appproject.group21.Database.Game;
import dk.au.mad21spring.appproject.group21.Factories.LatestGamesViewModelFactory;
import dk.au.mad21spring.appproject.group21.R;
import dk.au.mad21spring.appproject.group21.Viewmodels.FindGameViewModel;

public class FindGameActivity extends AppCompatActivity {
    //public static final String INDEX = "index";

    // widgets
    private Button btnBack, btnSearch;
    private CalendarView calendarView;
    private TextView txtHometeam, txtVisitorteam, txtScoreHometeam, txtScoreVisitorteam;

    //ViewModel
    private FindGameViewModel findGameViewModel;

    //Other
    private String teamName;
    private String date;
    private int season;

    public static final String TEAM = "TEAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);

        Intent data = getIntent();
        teamName = data.getStringExtra(TEAM);

        //create ViewModel
        findGameViewModel = new ViewModelProvider(this, new LatestGamesViewModelFactory(getApplication())).get(FindGameViewModel.class);
        findGameViewModel.getGame().observe(this, new Observer<Game>() {
            @Override
            public void onChanged(Game game) {
                showGameData(game);
            }
        });

        setupUI();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });


    }

    private void setupUI() {
        btnBack = findViewById(R.id.btnBackStats);
        btnSearch = findViewById(R.id.btnSearchGame);
        txtHometeam = findViewById(R.id.txtHometeam);
        txtVisitorteam = findViewById(R.id.txtVisitorTeam);
        txtScoreHometeam = findViewById(R.id.txtScoreHometeam);
        txtScoreVisitorteam = findViewById(R.id.txtScoreVisitorteam);
        calendarView = findViewById(R.id.calendarView);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int teamID = findGameViewModel.getTeam(teamName).getValue().getTeamID();
                findGameViewModel.loadGame(teamID, date);
            }
        });
    }

    private void showGameData(Game game) {
        String hometeam = game.getHometeam();
        String visitorteam = game.getVisitorteam();
        txtHometeam.setText(hometeam);
        txtVisitorteam.setText(visitorteam);

        int scoreHometeam = game.getHomeTeamScore();
        int scoreVisitorteam = game.getVisitorTeamScore();
        txtScoreHometeam.setText(scoreHometeam+"");
        txtScoreVisitorteam.setText(scoreVisitorteam+"");

    }
}