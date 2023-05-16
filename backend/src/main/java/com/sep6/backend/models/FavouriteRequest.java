package com.sep6.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavouriteRequest {
    private int accountId;
    private int movieId;
}
