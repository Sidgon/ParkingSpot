package database;

import android.provider.BaseColumns;

/**
 * Created on 22.04.2017.
 */

public final class ReservationContract {

    private ReservationContract(){
        //empty constructor should never be instantiated
    }

    public static abstract class ReservationEntry implements BaseColumns{
        //Table name
        public static final String TABLE_RESERVATION = "reservation";

        //Reservation Column names
        public static final String KEY_IDRESERVATION = "idreservation";
        public static final String KEY_IDPARKINGSPOT = "idparkingspot";
        public static final String KEY_IDPROVIDER = "idprovider";
        public static final String KEY_IDTENANT = "idtenant";
        public static final String KEY_DAYS = "days";
        public static final String KEY_FULLPRICE = "fullprice";
        public static final String KEY_ISRENTED = "isrented";


        //Database creation sql statement days
        public static final String CREATE_TABLE_RESERVATION = "CREATE TABLE "
                + TABLE_RESERVATION + "(" + KEY_IDRESERVATION + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_IDPARKINGSPOT + " INTEGER NOT NULL, "
                + KEY_IDPROVIDER + " INTEGER NOT NULL, "
                + KEY_IDTENANT + " INTEGER NOT NULL, "
                + KEY_DAYS + " INTEGER NOT NULL, "
                //        + KEY_ARRIVAL + " DATETIME NOT NULL, "
                //        + KEY_DEPARTING + " DATETIME NOT NULL, "
                + KEY_FULLPRICE + " REAL NOT NULL, "
                + KEY_ISRENTED + " INTEGER NOT NULL, "
                + "FOREIGN KEY ("+KEY_IDPARKINGSPOT+") REFERENCES "+ ParkingspotContract.ParkingspotEntry.TABLE_PARKINGSPOT+"("+ ParkingspotContract.ParkingspotEntry.KEY_IDPARKINGSPOT+"), "
                + "FOREIGN KEY ("+KEY_IDPROVIDER+") REFERENCES "+UserContract.UserEntry.TABLE_USER+"("+UserContract.UserEntry.KEY_IDUSER+"), "
                + "FOREIGN KEY ("+KEY_IDTENANT+") REFERENCES "+UserContract.UserEntry.TABLE_USER+"("+UserContract.UserEntry.KEY_IDUSER+"));";

    }


}
