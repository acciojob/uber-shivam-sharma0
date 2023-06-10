package com.driver.model;


import javax.persistence.*;


//@Table
@Entity
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int perKmRate;

    private  boolean available;

    @OneToOne
    @JoinColumn
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

//    public Cab() {
//    }
//
//    public Cab(Integer id, int perKmRate, boolean available) {
//        this.id = id;
//        this.perKmRate = perKmRate;
//        this.available = available;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}