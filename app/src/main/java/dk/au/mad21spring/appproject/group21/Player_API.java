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
import dk.au.mad21spring.appproject.group21.API_classes.BasketballPlayerAPI;

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


    public void getPlayer(String name, VolleyCallback callback) {
        String base = "https://www.balldontlie.io/api/v1/players?search="+name;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                sendRequest(base, false, callback);
            }
        });
    }


    private void sendRequest(String url, boolean update, VolleyCallback callback) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(app);
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Player player = parseJson(response, update);
                callback.onSuccesPlayer(player);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }


    private Player parseJson(String json, boolean update) {
        Gson gson = new GsonBuilder().create();
        BasketballPlayerAPI playerData = gson.fromJson(json, BasketballPlayerAPI.class);
        Player player = null;
        if (playerData != null) {
            player = new Player(playerData.getData().get(0).getId(),playerData.getData().get(0).getFirstName(), playerData.getData().get(0).getLastName(),
                    playerData.getData().get(0).getPosition(),playerData.getData().get(0).getHeightFeet(),playerData.getData().get(0).getHeightInches(),
                    playerData.getData().get(0).getWeightPounds(), playerData.getData().get(0).getTeam().getName());
        }
        return player;
    }
}
