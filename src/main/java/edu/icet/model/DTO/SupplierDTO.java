package edu.icet.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDTO {

    private String companyName;
    private String contactPerson;
    private long phone;
    private String address;
    private String email;

}
