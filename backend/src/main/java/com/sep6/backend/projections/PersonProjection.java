package com.sep6.backend.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sep6.backend.models.PersonType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.util.Date;

public interface PersonProjection {
    int getId();
    String getName();
    PersonType getType();
    Date getDateOfBirth();
    String getPlaceOfBirth();
    char getGender();
    String getBiography();
    Date getDeathDate();
    String getProfileImg();
}
