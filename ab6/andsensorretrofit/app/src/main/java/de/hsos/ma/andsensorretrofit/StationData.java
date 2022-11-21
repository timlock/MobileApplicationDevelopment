package de.hsos.ma.andsensorretrofit;
import com.google.gson.annotations.SerializedName;
public class StationData {
    @SerializedName("name")
    private final String name;
    private final int zip;
    private final String latitude;
    private final String longitude;
    private final double cost;
    public StationData(String name, int zip, String latitude, String longitude,
                       double cost) {
        this.name = name;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public int getZip() {
        return zip;
    }
    public double getCost() {
        return cost;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public String toString() {
        String s = new String();
        s = s.format ("%s, %d, %s", name, zip, cost);
        return s;
    }
}