
package dk.au.mad21spring.appproject.group21.Location_API_Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityWeather {

    @SerializedName("coord")
    @Expose
    private dk.au.mad21spring.appproject.group21.Location_API_Classes.Coord coord;
    @SerializedName("weather")
    @Expose
    private List<dk.au.mad21spring.appproject.group21.Location_API_Classes.Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private dk.au.mad21spring.appproject.group21.Location_API_Classes.Main main;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("rain")
    @Expose
    private dk.au.mad21spring.appproject.group21.Location_API_Classes.Rain rain;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    public dk.au.mad21spring.appproject.group21.Location_API_Classes.Coord getCoord() {
        return coord;
    }

    public void setCoord(dk.au.mad21spring.appproject.group21.Location_API_Classes.Coord coord) {
        this.coord = coord;
    }

    public List<dk.au.mad21spring.appproject.group21.Location_API_Classes.Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<dk.au.mad21spring.appproject.group21.Location_API_Classes.Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public dk.au.mad21spring.appproject.group21.Location_API_Classes.Main getMain() {
        return main;
    }

    public void setMain(dk.au.mad21spring.appproject.group21.Location_API_Classes.Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public dk.au.mad21spring.appproject.group21.Location_API_Classes.Rain getRain() {
        return rain;
    }

    public void setRain(dk.au.mad21spring.appproject.group21.Location_API_Classes.Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
