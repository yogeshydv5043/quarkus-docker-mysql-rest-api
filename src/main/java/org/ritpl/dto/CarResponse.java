package org.ritpl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse {

    private long carId;

    private String carName;

    private String company;

    private String carColor;

    private String model;

    private int modelYear;

    private double price;

    private boolean available = true;

}
