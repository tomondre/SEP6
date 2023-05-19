package com.sep6.backend.projections;

import java.sql.Date;

public interface AccountProjection {
    int getId();
    String getUsername();
    String getPassword();
    String getEmail();
    String getName();
    String getCountry();
    String getProfilePictureUrl();
    Date getDateOfBirth();
    String getGender();
}
