package models;

public class Country {
    private int id;
    private String code;
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

    public Country setLink(String link) {
        this.link = link;
        return this;
    }

    public int getZonesCount() {
        return zonesCount;
    }

    public Country setZonesCount(int zonesCount) {
        this.zonesCount = zonesCount;
        return this;
    }
}
