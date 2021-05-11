package dk.au.mad21spring.appproject.group21.Activities;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dk.au.mad21spring.appproject.group21.Factories.LocationViewModelFactory;
import dk.au.mad21spring.appproject.group21.Location_API.Coord;
import dk.au.mad21spring.appproject.group21.R;
import dk.au.mad21spring.appproject.group21.Viewmodels.LocationViewModel;
import dk.au.mad21spring.appproject.group21.VolleyCallback;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationViewModel vm;
    private String cityName;
    private Coord coord;

    public static final String CITY = "CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent data = getIntent();
        cityName = data.getStringExtra(CITY);

        vm = new ViewModelProvider(this, new LocationViewModelFactory(getApplication())).get(LocationViewModel.class);

        //getString(new VolleyCallback(){
        //    @Override
        //    public void onSucces(Coord result) {

        //    }

        //    @Override
        //    public void onError(Coord result) {

        //    }
        //});

        VolleyCallback callback = new VolleyCallback() {
            @Override
            public void onSucces(Coord result) {
                result.getLon();
                result.getLat();
            }

            @Override
            public void onError(Coord result) {

            }
        };

        vm.getLongLat(cityName, callback);

        //Læg koordinaterne i kortet på en sej måde

    }

    //private void getString(VolleyCallback volleyCallback) {
    //}


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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        /*
        LatLng city = new LatLng(coord.getLat(), coord.getLon());
        mMap.addMarker(new MarkerOptions().position(city).title("You are here!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(city));
        */
    }
}