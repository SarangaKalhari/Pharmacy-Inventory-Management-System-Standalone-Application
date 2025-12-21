package edu.icet.model.Entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesReport {

    private String invoice_id;
    private LocalDate date;
    private BigDecimal net_amount;
}
