package com.example.ezzeldeen.atm_exper.ViewPager_fragments;

public class ATM {
    String name ;
    String address ;
    double lng;
    double lat;
    double distance;
    private double balance;
    private int T_N_last_hour;
    private double precentagofdistance;
    private double precentagofbalance;
    private double precentagofcrowding;


    public ATM(String name , String address ,double lng , double lat , double distance ){
        this.name=name;
        this.address=address;
        this.lat=lat;
        this.lng=lng;
        this.distance=distance;


    }



    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public double getDistance() {
        return distance;
    }


    public double getbalance() {
        return balance;
    }

    public void setbalance(double balance) {
        this.balance = balance;
    }

    public int getT_N_last_hour() {
        return T_N_last_hour;
    }

    public void setT_N_last_hour(int T_N_last_hour) {
        this.T_N_last_hour = T_N_last_hour;
    }

    public ATM(double balance) {
        this.balance = balance;
    }

    public ATM(double balance, int T_N_last_hour, String name) {
        this.balance = balance;
        this.name=name;
        this.T_N_last_hour = T_N_last_hour;
    }
    public ATM(double precentingofdistance ,double precentagofbalance,double precentageofcrowding){
        this.precentagofdistance=precentingofdistance;
        this.precentagofbalance=precentagofbalance;
        this.precentagofcrowding=precentageofcrowding;
    }

    public void setPrecentagofdistance(double precentagofdistance) {
        this.precentagofdistance = precentagofdistance;
    }

    public void setPrecentagofbalance(double precentageofbalance) {
        this.precentagofbalance = precentageofbalance;
    }

    public void setPrecentagofcrowding(double precentagofcrowding) {
        this.precentagofcrowding = precentagofcrowding;
    }

    public double getPrecentagofdistance() {
        return precentagofdistance;
    }

    public double getPrecentagofbalance() {
        return precentagofbalance;
    }

    public double getPrecentagofcrowding() {
        return precentagofcrowding;
    }
}
