package edu.icet.model.Entity;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medicine {

    private String medicineID;
    private String name;
    private String brand;
    private String supplierID;
    private String batchNo;
    private int quantity;
    private BigDecimal cost;
    private BigDecimal unitPrice;
    private Date expiryDate;
    private String category;
}
