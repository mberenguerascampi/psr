package com.plubits.pokerspeedrange.utils;

/**
 * Created by Marc on 30/1/2016.
 */
public class Constants {
    public final static String JSON_FILENAME = "SpeedRange.json";
    public final static int TAB_CALL = 0;
    public final static int TAB_CALL_TO_PUSH = 1;
    public final static int NUM_TABS = 2;

    //JSON Keys
    public final static String PUSH_TAB_KEY = "push";
    public final static String CALL_TO_PUSH_TAB_KEY = "call_to_push";
    public final static String POSITIONS_LIST_KEY = "positions_list";
    public final static String BLINDS_LIST_KEY = "blinds_list";
    public final static String PERCENT_LIST_KEY = "percent";
    public final static String TABLE_KEY = "table";
    public final static String TABLE_VALUES_KEY = "table_values";
    public final static String ANTES_KEY = "ANTES";
    public final static String NO_ANTES_KEY = "NO_ANTES";

    //Values states
    public enum VALUES_STATES {
        UNSELECTED,
        ANTES,
        NO_ANTES
    }
}
