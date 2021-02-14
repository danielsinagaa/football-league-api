package com.enigma.Football.models.teams;

import com.enigma.Football.models.pages.PageSearch;
import com.enigma.Football.models.validations.Alphabetic;

public class TeamSearch extends PageSearch {
    @Alphabetic
    private String name;
}
