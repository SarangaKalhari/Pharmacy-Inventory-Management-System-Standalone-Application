package edu.icet.model.Entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sale {

    private int id;
    private String invoice_id;
    private String medicine_id;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;

}
