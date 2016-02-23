package com.plubits.pokerspeedrange.models;

import com.plubits.pokerspeedrange.utils.Constants;

/**
 * Created by Marc on 12/2/2016.
 */
public class TableValue {
    public String valueID;
    public Constants.VALUES_STATES state;

    public TableValue(String valueID, Constants.VALUES_STATES state) {
        this.state = state;
        this.valueID = valueID;
    }
}
