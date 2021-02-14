package com.enigma.Football.models.referee;

import com.enigma.Football.models.pages.PageSearch;
import com.enigma.Football.models.validations.Alphabetic;

public class RefereeSearch extends PageSearch {
    @Alphabetic
    private String name;
}
