package com.narendra.timetable.Database;

import android.provider.BaseColumns;

public class TineTableContract {
    private TineTableContract() {
    }

    public static final class TableEntry implements BaseColumns {
        /**
         * Name of database table
         */
        public final static String TABLE_NAME = "timeTable";

        /**
         * Table Fields
         */
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TIMETABLE_NAME = "name";
        public final static String COLUMN_NO_OF_PERIODS_NUMBER = "noOfPeriods";
        public final static String COLUMN_NO_OF_ROWS = "noOfRows";

    }
}
