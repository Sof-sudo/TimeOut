package dk.au.mad21spring.appproject.group21.Activities;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dk.au.mad21spring.appproject.group21.Factories.LocationViewModelFactory;
import dk.au.mad21spring.appproject.group21.Location_API_Classes.Coord;
import dk.au.mad21spring.appproject.group21.R;
import dk.au.mad21spring.appproject.group21.Viewmodels.LocationViewModel;
import dk.au.mad21spring.appproject.group21.Interfaces.VolleyCallbackLocation;

//Inspired from lesson 9 and the democode from the lesson
public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationViewModel vm;
    private String cityName;
    private double lon;
    private double lat;

    private Button btnBack;
    private Button btnMapType;

    public static final String CITY = "CITY";

    private int mapType = GoogleMap.MAP_TYPE_NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        Intent data = getIntent();
        cityName = data.getStringExtra(CITY);

        vm = new ViewModelProvider(this, new LocationViewModelFactory(getApplication())).get(LocationViewModel.class);

        //Callback function med inspiration fra https://stackoverflow.com/questions/28120029/how-can-i-return-value-from-function-onresponse-of-volley
        VolleyCallbackLocation callback = new VolleyCallbackLocation() {
            @Override
            public void onSucces(Coord result) {
                lon = result.getLon();
                lat = result.getLat();

                setUpMap();
            }

            @Override
            public void onError() {
                lon = 0;
                lat = 0;
                setUpMap();
            }
        };

        vm.getLongLat(cityName, callback);

        btnBack = findViewById(R.id.btnBackLocation);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });

        btnMapType = findViewById(R.id.btnMapTypeLocation);
        btnMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeMapType();
            }
        });

    }

    private void Back(){
        finish();
    }

    //Code inspired from democode of TheArnieExerciseFinder
    private void ChangeMapType(){
        String s = "";
        if(mapType == GoogleMap.MAP_TYPE_SATELLITE){
            mapType = GoogleMap.MAP_TYPE_HYBRID;
            s = "Hybrid";
        } else if(mapType == GoogleMap.MAP_TYPE_HYBRID){
            mapType = GoogleMap.MAP_TYPE_NORMAL;
            s = "Normal";
        } else if(mapType == GoogleMap.MAP_TYPE_NORMAL){
            mapType = GoogleMap.MAP_TYPE_TERRAIN;
            s = "Terrain";
        } else if(mapType == GoogleMap.MAP_TYPE_TERRAIN){
            mapType = GoogleMap.MAP_TYPE_SATELLITE;
            s = "Satellite";
        }

        mMap.setMapType(mapType);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void setUpMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        if (lat != 0 && lon != 0) {
            mMap = googleMap;
            LatLng city = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(city).title("You are here!"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(city));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(city.latitude, city.longitude), 10));
//        }
    }
}