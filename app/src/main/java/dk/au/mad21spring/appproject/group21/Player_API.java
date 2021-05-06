package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Players_API.BasketballPlayersAPI;
import dk.au.mad21spring.appproject.group21.Teams_API.BasketBallTeamsAPI;

public class Player_API {
    private ExecutorService executor;
    private Application app;
    private Repository repository;


    public Player_API(Application app, Repository repository) {

        executor = Executors.newSingleThreadExecutor();
        this.app = app;
        this.repository = repository;
    }

    private RequestQueue requestQueue;


    public void getAllPlayers(){
        String base = "https://www.balldontlie.io/api/v1/players/";
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
        BasketballPlayersAPI playerData = gson.fromJson(json, BasketballPlayersAPI.class);
        if (playerData!= null) {
            Player player = new Player(playerData.getId(), playerData.getFirstName(), playerData.getLastName(), playerData.getPosition(),
                    playerData.getHeightFeet(), playerData.getHeightInches(), playerData.getWeightPounds());

            //repository.addPlayerAsynch(player);
        }


    }
}
