package dk.au.mad21spring.appproject.group21;

import android.app.Application;
import android.app.DownloadManager;

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

import dk.au.mad21spring.appproject.group21.API_AllTeams.AllTeams;


public class WEP_API {

    private ExecutorService executor;
    private Application app;


    public WEP_API(Application app) {

        executor = Executors.newSingleThreadExecutor();
        this.app = app;
    }

    private RequestQueue requestQueue;


    private void addTeam(String teamNumber) {
        String base = "https://www.balldontlie.io/api/v1/teams/" + teamNumber;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                sendRequest(base, false);
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
        AllTeams teamData = gson.fromJson(json, AllTeams.class);
        if(teamData != null){
            Team team = new Team("teamName","CityName");
        }



    }


}
