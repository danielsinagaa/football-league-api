package com.enigma.Football.models.resultstats;

import com.enigma.Football.models.players.PlayerResponse;
import com.enigma.Football.models.results.ResultResponse;

public class ResultStatResponse {
    private String id;
    private PlayerResponse player;
    private ResultResponse result;
    private Integer goal;
    private Integer assit;
    private Integer yellowCard;
    private Integer redCard;
}
