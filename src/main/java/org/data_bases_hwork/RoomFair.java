package org.data_bases_hwork;

public class RoomFair {

    private int id;
    private double value;
    private String season;

    public RoomFair(int id, double value, String season) {
        this.id = id;
        this.value = value;
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getSeason() {
        return season;
    }


}
