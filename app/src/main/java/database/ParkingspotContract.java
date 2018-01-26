package database;

import android.provider.BaseColumns;

/**
 * Created on 22.04.2017.
 */

public final class ParkingspotContract {

    private ParkingspotContract(){
        //empty constructor should never be instantiated
    }

    public static abstract class ParkingspotEntry implements BaseColumns{
        //Table name
        public static final String TABLE_PARKINGSPOT = "parkingspot.android";

        //Parkingspot Column names
        public static final String KEY_IDPARKINGSPOT = "idparkingspot";
        public static final String KEY_IDUSER = "iduser";
        public static final String KEY_ADDRESS = "address";
        public static final String KEY_LOCATION = "location";
        public static final String KEY_PRICE = "price";
        public static final String KEY_COORDINATEX = "coordinatex";
        public static final String KEY_COORDINATEY = "coordinatey";

        //Database creation sql statement
        public static final String CREATE_TABLE_PARKINGSPOT = "CREATE TABLE "
                + TABLE_PARKINGSPOT + "(" + KEY_IDPARKINGSPOT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_IDUSER + " INTEGER NOT NULL, "
                + KEY_ADDRESS + " TEXT NOT NULL, "
                + KEY_LOCATION + " TEXT NOT NULL, "
                + KEY_PRICE + " REAL NOT NULL, "
                + KEY_COORDINATEX + " REAL, "
                + KEY_COORDINATEY + " REAL, "
                + "FOREIGN KEY ("+KEY_IDUSER+") REFERENCES "+UserContract.UserEntry.TABLE_USER+"("+UserContract.UserEntry.KEY_IDUSER+"));";
    }
}
