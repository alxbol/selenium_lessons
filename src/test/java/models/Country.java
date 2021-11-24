package models;

public class Country {
    private String name;
    private String link;
    private int zonesCount;

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public String getLink() {
        return link;
    }

    public int getZonesCount() {
        return zonesCount;
    }

    public Country setZonesCount(int zonesCount) {
        this.zonesCount = zonesCount;
        return this;
    }
}
