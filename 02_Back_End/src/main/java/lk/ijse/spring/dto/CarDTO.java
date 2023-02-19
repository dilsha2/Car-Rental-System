package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CarDTO {
    private String registration_no;
    private String brand;
    private String type;
    private String transmission;
    private String color;
    private int no_of_passengers;
    private long mileage;
    private String fuelType;
    private double daily_rate;
    private double monthly_rate;
    private double price_for_extra_km;
    private double waiver_payment;
    private String status;
}
