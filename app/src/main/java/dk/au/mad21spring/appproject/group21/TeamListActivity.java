package dk.au.mad21spring.appproject.group21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import dk.au.mad21spring.appproject.group21.Database.Teams;

public class TeamListActivity extends AppCompatActivity implements TeamAdapter.ITeamItemClickedListener {

    // ui widgets
    private RecyclerView rcvList;
    private TeamAdapter adapter;

    // viewmodel
    private TeamListViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = new ViewModelProvider(this).get(TeamListViewModel.class);
        vm.getTeams().observe(this, new Observer<ArrayList<Teams>>() {
            @Override
            public void onChanged(ArrayList<Teams> list) {
                adapter.updateTeamList(list);
            }
        });

        setupUI();
    }

    private void setupUI() {
        adapter = new TeamAdapter(this);
        rcvList = findViewById(R.id.rcvTeams);
        rcvList.setLayoutManager(new LinearLayoutManager(this));
        rcvList.setAdapter(adapter);
    }

    @Override
    public void onTeamClicked(int index) {

    }
}