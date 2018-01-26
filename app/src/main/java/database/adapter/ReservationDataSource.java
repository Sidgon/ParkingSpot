package database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import database.DbHelper;
import database.ReservationContract;
import database.object.Reservation;

/**
 * Created on 23.04.2017.
 */

public class ReservationDataSource {

    private SQLiteDatabase db;
    private Context context;

    public ReservationDataSource(Context context){
        DbHelper dbHelper = DbHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a Reservation
     */
    public long createReservation(Reservation reservation){
        long id;
        ContentValues values = new ContentValues();
        values.put(ReservationContract.ReservationEntry.KEY_IDPARKINGSPOT, reservation.getIdparkingspot());
        values.put(ReservationContract.ReservationEntry.KEY_IDPROVIDER, reservation.getIdprovider());
        values.put(ReservationContract.ReservationEntry.KEY_IDTENANT, reservation.getIdtenant());
        values.put(ReservationContract.ReservationEntry.KEY_DAYS, reservation.getDays());
        values.put(ReservationContract.ReservationEntry.KEY_FULLPRICE, reservation.getDays());
        values.put(ReservationContract.ReservationEntry.KEY_ISRENTED, reservation.getIsrented());

        id = this.db.insert(ReservationContract.ReservationEntry.TABLE_RESERVATION, null, values);

        return id;
    }


    public List<Reservation> getReservationByTenant(long id){
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "SELECT * FROM " + ReservationContract.ReservationEntry.TABLE_RESERVATION +
                " WHERE " + ReservationContract.ReservationEntry.KEY_IDTENANT + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Reservation reservation = new Reservation();
                reservation.setIdreservation(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDRESERVATION)));
                reservation.setIdparkingspot(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDPARKINGSPOT)));
                reservation.setIdprovider(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDPROVIDER)));
                reservation.setIdtenant(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDTENANT)));
                reservation.setDays(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_DAYS)));
                reservation.setFullprice(cursor.getDouble(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_FULLPRICE)));
                reservation.setIsrented(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_ISRENTED)));

                reservations.add(reservation);
            }while(cursor.moveToNext());
        }

        return reservations;
    }

    /**
     * Get All Reservations
     */
    public List<Reservation> getAllReservations(){
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "SELECT * FROM " + ReservationContract.ReservationEntry.TABLE_RESERVATION + " ORDER BY " + ReservationContract.ReservationEntry.KEY_IDRESERVATION;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Reservation reservation = new Reservation();
                reservation.setIdreservation(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDRESERVATION)));
                reservation.setIdparkingspot(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDPARKINGSPOT)));
                reservation.setIdprovider(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDPROVIDER)));
                reservation.setIdtenant(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDTENANT)));
                reservation.setDays(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_DAYS)));
                reservation.setFullprice(cursor.getDouble(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_FULLPRICE)));
                reservation.setIsrented(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_ISRENTED)));

                reservations.add(reservation);
            }while(cursor.moveToNext());
        }

        return reservations;
    }

    public List<Reservation> getAllReservationsWithUser(long id){
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "SELECT * FROM " + ReservationContract.ReservationEntry.TABLE_RESERVATION +
                " WHERE " + ReservationContract.ReservationEntry.KEY_IDPROVIDER + " = " + id + " OR " + ReservationContract.ReservationEntry.KEY_IDTENANT + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Reservation reservation = new Reservation();
                reservation.setIdreservation(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDRESERVATION)));
                reservation.setIdparkingspot(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDPARKINGSPOT)));
                reservation.setIdprovider(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDPROVIDER)));
                reservation.setIdtenant(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDTENANT)));
                reservation.setDays(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_DAYS)));
                reservation.setFullprice(cursor.getDouble(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_FULLPRICE)));
                reservation.setIsrented(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_ISRENTED)));

                reservations.add(reservation);
            }while(cursor.moveToNext());
        }

        return reservations;
    }

    public List<Reservation> getAllReservationsByParkingspotId(long id){
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "SELECT * FROM " + ReservationContract.ReservationEntry.TABLE_RESERVATION +
                " WHERE " + ReservationContract.ReservationEntry.KEY_IDPARKINGSPOT + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Reservation reservation = new Reservation();
                reservation.setIdreservation(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDRESERVATION)));
                reservation.setIdparkingspot(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDPARKINGSPOT)));
                reservation.setIdprovider(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDPROVIDER)));
                reservation.setIdtenant(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_IDTENANT)));
                reservation.setDays(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_DAYS)));
                reservation.setFullprice(cursor.getDouble(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_FULLPRICE)));
                reservation.setIsrented(cursor.getInt(cursor.getColumnIndex(ReservationContract.ReservationEntry.KEY_ISRENTED)));

                reservations.add(reservation);
            }while(cursor.moveToNext());
        }

        return reservations;
    }

    /**
     * Delete a Reservation
     */
    public void deleteReservation(long id){
        this.db.delete(ReservationContract.ReservationEntry.TABLE_RESERVATION, ReservationContract.ReservationEntry.KEY_IDRESERVATION + " = ?",
                new String[] { String.valueOf(id)});
    }
}
