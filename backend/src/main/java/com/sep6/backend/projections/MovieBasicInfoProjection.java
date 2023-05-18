package com.sep6.backend.projections;

import com.sep6.backend.models.Genre;

import java.sql.Date;
import java.util.List;

public interface MovieBasicInfoProjection {
    int getId();
    String getTitle();
    String getDescription();
    String getPosterUrl();
    int getRuntime();
    String getLanguage();
    int getBoxOffice();
    int getBudget();
    String getStatus();
    Date getReleaseDate();
    List<Genre> getGenres();
    double getRating();
}
