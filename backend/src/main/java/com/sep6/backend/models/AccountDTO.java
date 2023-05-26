package com.sep6.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO
{
    private String name;
    private String email;
    private String country;
    private String gender;
    private Date dateOfBirth;
}
