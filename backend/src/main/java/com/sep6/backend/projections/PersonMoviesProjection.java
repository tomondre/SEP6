package com.sep6.backend.projections;

import java.util.List;

public interface PersonMoviesProjection extends PersonProjection {
    List<MovieProjection> getMovies();
    double getRatingAverage();
}
