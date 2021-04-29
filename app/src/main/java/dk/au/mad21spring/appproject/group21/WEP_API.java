package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Teams_API.BasketBallTeamsAPI;

public class WEP_API {

    private ExecutorService executor;
    private Application app;
    private Repository repository;


    public WEP_API(Application app, Repository repository) {

        executor = Executors.newSingleThreadExecutor();
        this.app = app;
        this.repository = repository;
    }

    private RequestQueue requestQueue;


    public void getAllTeams() {
        String base = "https://www.balldontlie.io/api/v1/teams/";
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //sendRequest(base, false);
                for(int i=1; i<31; i++){ // there is 30 teams
                    sendRequest(base + i, false);
                }
            }
        });
    }

    // ud fra deres id, skal lige finde ud af hvordan vi skal hente spillerne
    // kan være vi skal have en ny WEP_API klasse til det?
    private void getPlayers(int playerNumber){
        String base = "https://www.balldontlie.io/api/v1/players/" + playerNumber;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                sendRequest(base,false);
            }
        });
    }

    private void sendRequest(String url, boolean update) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(app);
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJson(response, update);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }


    private void parseJson(String json, boolean update) {
        Gson gson = new GsonBuilder().create();
        BasketBallTeamsAPI teamData = gson.fromJson(json, BasketBallTeamsAPI.class);
        if (teamData != null) {
            Team team = new Team(teamData.getId(), teamData.getAbbreviation(), teamData.getCity(),
                    teamData.getConference(), teamData.getDivision(), teamData.getFullName(), teamData.getName());

            repository.addTeamAsynch(team);
        }


    }


}
