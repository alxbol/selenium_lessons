package models;

import java.util.Objects;

public class Product {
    private String name;
    private String campaignPrice;
    private String campaignPriceColor;
    private String campaignPriceTextDecoration;
    private String campaignPriceFontSize;
    private String regularPrice;
    private String regularPriceColor;
    private String regularPriceTextDecoration;
    private String regularPriceFontSize;

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getCampaignPrice() {
        return campaignPrice;
    }

    public Product setCampaignPrice(String campaignPrice) {
        this.campaignPrice = campaignPrice;
        return this;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public Product setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public String getRegularPriceColor() {
        return regularPriceColor;
    }

    public Product setRegularPriceColor(String regularPriceColor) {
        this.regularPriceColor = regularPriceColor;
        return this;
    }

    public String getRegularPriceTextDecoration() {
        return regularPriceTextDecoration;
    }

    public Product setRegularPriceTextDecoration(String regularPriceTextDecoration) {
        this.regularPriceTextDecoration = regularPriceTextDecoration;
        return this;
    }

    public String getCampaignPriceColor() {
        return campaignPriceColor;
    }

    public Product setCampaignPriceColor(String campaignPriceColor) {
        this.campaignPriceColor = campaignPriceColor;
        return this;
    }

    public String getCampaignPriceTextDecoration() {
        return campaignPriceTextDecoration;
    }

    public Product setCampaignPriceTextDecoration(String campaignPriceTextDecoration) {
        this.campaignPriceTextDecoration = campaignPriceTextDecoration;
        return this;
    }

    public String getCampaignPriceFontSize() {
        return campaignPriceFontSize;
    }

    public Product setCampaignPriceFontSize(String campaignPriceFontSize) {
        this.campaignPriceFontSize = campaignPriceFontSize;
        return this;
    }

    public String getRegularPriceFontSize() {
        return regularPriceFontSize;
    }

    public Product setRegularPriceFontSize(String regularPriceFontSize) {
        this.regularPriceFontSize = regularPriceFontSize;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(campaignPrice, product.campaignPrice) && Objects.equals(regularPrice, product.regularPrice);
    }
}
