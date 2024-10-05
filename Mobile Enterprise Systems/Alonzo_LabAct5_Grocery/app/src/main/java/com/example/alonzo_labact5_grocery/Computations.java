package com.example.alonzo_labact5_grocery;

import java.util.ArrayList;

public class Computations {
    public static Computations instance = null;
    public ArrayList<String> itemNames;
    public ArrayList<Double> prices;
    public ArrayList<Integer> quantities;
    public ArrayList<Double> amounts;

    public String nameReceipt;
    public String priceReceipt;
    public String quantityReceipt;
    public String amountsReceipt;
    public Double priceTotal;

    public Computations(){
        itemNames = new ArrayList<>();
        prices = new ArrayList<>();
        quantities = new ArrayList<>();
        amounts = new ArrayList<>();
        nameReceipt = "";
        priceReceipt = "";
        quantityReceipt = "";
        priceTotal = 0.0;
    }

    public static Computations getInstance(){
        if (instance == null) {
            instance = new Computations();
        }
        return instance;
    }

    public void addItem(String itemName, Double price, Integer quantity){
        if(itemNames.contains(itemName)){
            int index = itemNames.indexOf(itemName);
            prices.set(index, price);
            quantities.set(index, quantity);
            amounts.set(index, quantity * price);
            return;
        }

        itemNames.add(itemName);
        prices.add(price);
        quantities.add(quantity);
        amounts.add(quantity * price);
    }

    public boolean isAlreadyAdded(String itemName){
        return itemNames.contains(itemName);
    }

    public void createReceipt(){
        StringBuilder namesList = new StringBuilder();
        StringBuilder pricesList = new StringBuilder();
        StringBuilder quantityList = new StringBuilder();
        StringBuilder amountList = new StringBuilder();

        for(String item: itemNames){
            namesList.append(item).append("\n\n");
            int itemIndex = itemNames.indexOf(item);
            pricesList.append(prices.get(itemIndex)).append("\n\n");
            quantityList.append(quantities.get(itemIndex)).append("\n\n");
            amountList.append(amounts.get(itemIndex)).append("\n\n");
        }

        nameReceipt = namesList.toString();
        priceReceipt = pricesList.toString();
        quantityReceipt = quantityList.toString();
        amountsReceipt = amountList.toString();
    }

    public void setTotal(){
        priceTotal = 0.0;
        for(String item: itemNames){
            int itemIndex = itemNames.indexOf(item);
            priceTotal += prices.get(itemIndex) * quantities.get(itemIndex);
        }
    }

    public String getNameReceipt(){ return nameReceipt;}
    public String getPriceReceipt() { return priceReceipt;}
    public String getQuantityReceipt() { return quantityReceipt;}
    public String getAmountsReceipt() { return amountsReceipt;}
    public Double getTotal(){ setTotal(); return priceTotal;}

    public boolean isItemListEmpty(){
        return itemNames.isEmpty();
    }

    public void clearItems(){
        itemNames.clear();
        prices.clear();
        quantities.clear();
        amounts.clear();
    }
}
