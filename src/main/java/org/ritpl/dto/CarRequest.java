package org.ritpl.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequest {

    private String carName;

    private String company;

    private String carColor;

    private String model;

    private int modelYear;

    private double price;

}
