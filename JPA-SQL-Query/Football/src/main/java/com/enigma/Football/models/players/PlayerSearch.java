package com.enigma.Football.models.players;

import com.enigma.Football.models.pages.PageSearch;
import com.enigma.Football.models.validations.Alphabetic;

public class PlayerSearch extends PageSearch {
    @Alphabetic
    private String name;
}
