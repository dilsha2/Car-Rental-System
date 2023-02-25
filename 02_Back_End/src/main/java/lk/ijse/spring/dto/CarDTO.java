package lk.ijse.spring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CarDTO {
    private String registrationId;
    private String brand;
    private String type;
    private String model;
    private String fuelType;
    private String transmissionType;
    private String color;
    private int noOfPassenger;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private double mileage;
    private double freeKmDay;
    private long freeKmMonth;
    private double dailyRate;
    private double monthlyRate;
    private double priceForExtraKm;
    private double waiver_payment;
    private String availability;



}
