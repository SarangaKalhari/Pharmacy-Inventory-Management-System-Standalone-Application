package edu.icet.model.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaleDTO {

    private String itemID;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal total;

}
