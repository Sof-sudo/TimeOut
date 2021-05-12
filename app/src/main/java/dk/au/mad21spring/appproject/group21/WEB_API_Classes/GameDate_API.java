package dk.au.mad21spring.appproject.group21.WEB_API_Classes;

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

import dk.au.mad21spring.appproject.group21.Database.Game;
import dk.au.mad21spring.appproject.group21.Game_API_Classes.GamesAPI;
import dk.au.mad21spring.appproject.group21.Interfaces.VolleyCallbackGame;
import dk.au.mad21spring.appproject.group21.Repository;

public class GameDate_API {
    private ExecutorService executor;
    private Application app;
    private Repository repository;


    public GameDate_API(Application app, Repository repository) {

        executor = Executors.newSingleThreadExecutor();
        this.app = app;
        this.repository = repository;
    }

    private RequestQueue requestQueue;


    public void getGame(int teamID, String date, VolleyCallbackGame callback) {
        String base = "https://www.balldontlie.io/api/v1/games?team_ids[]="+teamID+"&dates[]="+date;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                sendRequest(base, callback);
            }
        });
    }


    private void sendRequest(String url, VolleyCallbackGame callback) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(app);
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Game game = parseJson(response);
                if (game == null)
                {
                    callback.onErrorGame();
                }
                else {
                    callback.onSuccesGame(game);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }


    private Game parseJson(String json) {
        Gson gson = new GsonBuilder().create();
        GamesAPI gameData = gson.fromJson(json, GamesAPI.class);
        Game game = null;

        if(gameData.getData().size() == 1)
        {
            game = new Game(gameData.getData().get(0).getId(), gameData.getData().get(0).getDate(), gameData.getData().get(0).getHomeTeamScore(),
                    gameData.getData().get(0).getVisitorTeamScore(), gameData.getData().get(0).getSeason(), gameData.getData().get(0).getPeriod(),
                    gameData.getData().get(0).getStatus(), gameData.getData().get(0).getTime(), gameData.getData().get(0).getPostseason(),
                    gameData.getData().get(0).getHomeTeam().getFullName(), gameData.getData().get(0).getVisitorTeam().getFullName());
        }

        return game;
    }
}
