package com.example.androidrrr.bakingappnd.model;


public class IngredientModel {


    private String ingredient1;
    private String quantity1;
    private String measure1;
    public IngredientModel(String ingredient, String quantity, String measure)
    {
        ingredient1=ingredient;
        quantity1=quantity;
        measure1=measure;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(String quantity1) {
        this.quantity1 = quantity1;
    }

    public String getMeasure1() {
        return measure1;
    }

    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
    }
}
