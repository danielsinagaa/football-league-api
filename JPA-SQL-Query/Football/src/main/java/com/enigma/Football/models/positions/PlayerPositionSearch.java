package com.enigma.Football.models.positions;

import com.enigma.Football.models.pages.PageSearch;
import com.enigma.Football.models.validations.Alphabetic;

public class PlayerPositionSearch extends PageSearch {
    @Alphabetic
    private String name;
}