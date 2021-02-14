package com.enigma.Football.models.positions;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PlayerPositionRequest {
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
