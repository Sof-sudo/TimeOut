package dk.au.mad21spring.appproject.group21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TeamAdapter.ITeamItemClickedListener {

    // ui widgets
    private RecyclerView rcvTeams;
    private TeamAdapter adapter;

    // viewModel
    private ListViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = new ViewModelProvider(this).get(ListViewModel.class);
        vm.getTeams().observe(this, new Observer<ArrayList<Team>>() {
            @Override
            public void onChanged(ArrayList<Team> list) {
                adapter.updateTeamList(list);
            }
        });

        setupUI();

    }

    private void setupUI() {
        adapter = new TeamAdapter(this);
        rcvTeams = findViewById(R.id.rcvTeams);
        rcvTeams.setLayoutManager(new LinearLayoutManager(this));
        rcvTeams.setAdapter(adapter);
    }

    @Override
    public void onTeamClicked(int index) {

    }
}