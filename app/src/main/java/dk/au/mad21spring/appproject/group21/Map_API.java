package dk.au.mad21spring.appproject.group21;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

import dk.au.mad21spring.appproject.group21.Location_API.CityWeather;
import dk.au.mad21spring.appproject.group21.Location_API.Coord;

public class Map_API {

    private ExecutorService executor;
    private Application app;
    private Repository repository;

    public Map_API(Application app, Repository repository) {

        executor = Executors.newSingleThreadExecutor();
        this.app = app;
        this.repository = repository;
    }

    private RequestQueue requestQueue;

    public void getLongLat(String cityName, final VolleyCallback callback) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=8b94ba400f3f60b2d6f7fc1980b6f4fe&units=metric";
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (requestQueue == null) {
                    requestQueue = Volley.newRequestQueue(app);
                }
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Coord coordFromJson = parseJsonLongLat(response);
                        callback.onSucces(coordFromJson);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(app, "The city cannot be found", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
    }

    private Coord parseJsonLongLat(String json) {
        Gson gson = new GsonBuilder().create();
        CityWeather cityWeather = gson.fromJson(json, CityWeather.class);
        Coord coord = new Coord();
        coord.setLat(cityWeather.getCoord().getLat());
        coord.setLon(cityWeather.getCoord().getLon());
        return coord;
    }

}
