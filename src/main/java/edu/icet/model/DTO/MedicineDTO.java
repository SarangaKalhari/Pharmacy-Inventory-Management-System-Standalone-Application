package edu.icet.model.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicineDTO {

    private String name;
    private String brand;
    private String supplierID;
    private String batchNo;
    private int quantity;
    private BigDecimal cost;
    private BigDecimal unitPrice;
    private LocalDate expiryDate;
    private String category;

}
