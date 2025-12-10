package edu.icet.model.Entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {

    private String supplier_id;
    private String company_name;
    private String contact_person;
    private long phone;
    private String email;
    private String address;
    private Timestamp created_at;


}
