package org.ritpl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Car_Showroom")
@Setter
@Getter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private long carId;

    @Column(name = "car_name")
    private String carName;

    @Column(name = "company_name")
    private String company;

    @Column(name="car_color")
    private String carColor;

    @Column(name = "car_model")
    private String model;

    @Column(name = "model_year")
    private int modelYear;

    @Column(name = "car_price")
    private double price;

    @Column(name = "available")
    @Transient
    private boolean available = true;
}
