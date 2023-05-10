package org.data_bases_hwork;

public class Accommodation {

    private int id;
    private String type;

    private String bed_type;

    private int max_guests;

    private String description;

    public Accommodation(int id, String type, String bed_type, int max_guests, String description) {
        this.id = id;
        this.type = type;
        this.bed_type = bed_type;
        this.max_guests = max_guests;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBed_type() {
        return bed_type;
    }

    public int getMax_guests() {
        return max_guests;
    }

    public String getDescription() {
        return description;
    }


}
