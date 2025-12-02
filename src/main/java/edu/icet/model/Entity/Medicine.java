package edu.icet.model.Entity;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medicine {

    private String medicine_id;
    private String name;
    private String brand;
    private String supplier_id;
    private String batch_no;
    private int quantity;
    private BigDecimal cost;
    private BigDecimal unit_price;
    private LocalDate expiry_date;
    private String category;
}
