package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 22.04.2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //Infos about database
    private static final String DATABASE_NAME = "parkingspot.android.db";
    private static final int DATABASE_VERSION = 1;
    private static DbHelper instance;

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    public static DbHelper getInstance(Context context){
        if(instance == null){
            instance = new DbHelper(context.getApplicationContext());
            //Enable foreign key support
            instance.db.execSQL("PRAGMA foreign_keys = ON;");
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(UserContract.UserEntry.CREATE_TABLE_USER);
        database.execSQL(ParkingspotContract.ParkingspotEntry.CREATE_TABLE_PARKINGSPOT);
        database.execSQL(ReservationContract.ReservationEntry.CREATE_TABLE_RESERVATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + ParkingspotContract.ParkingspotEntry.TABLE_PARKINGSPOT);
        db.execSQL("DROP TABLE IF EXISTS " + ReservationContract.ReservationEntry.TABLE_RESERVATION);

        //create new tables
        onCreate(db);
    }
}