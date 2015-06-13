package com.fatuhiva.model;

import java.util.UUID;

public class FatuElement implements IFatuElement {

    private String id;

    public FatuElement() {
        id = UUID.randomUUID().toString();
    }

    public final String getId() {
        return this.id;
    }

}
