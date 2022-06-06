package io.HaiderRizviLtd.coronacasestracker.models;

public class LocationStats {
    
    private String state;
    private String country;
    private int latestTotalCases;

    // Getters and setters
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getLatestTotalCases() {
        return latestTotalCases;
    }
    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }
    public void setLatestTotalCases(String string) {
    }
    @Override
    public String toString() {
        return "LocationStats [country=" + country 
        + ", latestTotalCases=" + latestTotalCases 
        + ", state=" + state
                + "]";
    }

    
    
    
}
