package dk.au.mad21spring.appproject.group21.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Activities.TeamActivity;
import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Factories.TeamListViewModelFactory;
import dk.au.mad21spring.appproject.group21.ForegroundService;
import dk.au.mad21spring.appproject.group21.R;
import dk.au.mad21spring.appproject.group21.TeamAdapter;
import dk.au.mad21spring.appproject.group21.Viewmodels.TeamListViewModel;

public class TeamListActivity extends AppCompatActivity implements TeamAdapter.ITeamItemClickedListener {

    public static final String TEAM = "TEAM";

    // ui widgets
    private RecyclerView rcvList;
    private TeamAdapter adapter;
    private Button search;
    private EditText searchField;

    // viewmodel
    private TeamListViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamlist);

        startService();

        vm = new ViewModelProvider(this, new TeamListViewModelFactory(getApplication())).get(TeamListViewModel.class);
        vm.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> list) {
                adapter.updateTeamList(list);
            }
        });

        setupUI();
        vm.addTeams(); // adds all the teams from the API.

        searchField = findViewById(R.id.txtSearch);

        search = findViewById(R.id.btnSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search();
            }
        });
    }

    private void startService() {
        Intent foregroundServiceIntent = new Intent(this, ForegroundService.class);
        startService(foregroundServiceIntent);
    }

    private void Search(){
        String teamSearch = searchField.getText().toString();
        Team team = vm.searchForTeam(teamSearch);

        if(team!=null){
            Intent intent = new Intent(this, TeamActivity.class);
            intent.putExtra(TEAM,team.getName());
            startActivity(intent);
        } else {

        }

        searchField.setText("");
    }

    private void setupUI() {
        adapter = new TeamAdapter(this);
        rcvList = findViewById(R.id.rcvTeams);
        rcvList.setLayoutManager(new LinearLayoutManager(this));
        rcvList.setAdapter(adapter);
    }

    @Override
    public void onTeamClicked(int index) {
        Team team = vm.getTeams().getValue().get(index);
        Intent intent = new Intent(this, TeamActivity.class);
        intent.putExtra(TEAM,team.getName());
        startActivity(intent);

    }
}