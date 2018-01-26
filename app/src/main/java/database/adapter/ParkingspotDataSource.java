package database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import database.DbHelper;
import database.ParkingspotContract;
import database.object.Parkingspot;
import database.object.Reservation;

/**
 * Created on 23.04.2017.
 */

public class ParkingspotDataSource {

    private SQLiteDatabase db;
    private Context context;

    public ParkingspotDataSource(Context context){
        DbHelper dbHelper = DbHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    /*
     * Insert a new Parkingspot
     */
    public long createParkingspot(Parkingspot parkingspot){
        long id;
        ContentValues values = new ContentValues();
        values.put(ParkingspotContract.ParkingspotEntry.KEY_IDUSER, parkingspot.getIduser());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_ADDRESS, parkingspot.getAddress());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_LOCATION, parkingspot.getLocation());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_PRICE, parkingspot.getPrice());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_COORDINATEX, parkingspot.getCoordinatex());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_COORDINATEY, parkingspot.getCoordinatey());

        id = this.db.insert(ParkingspotContract.ParkingspotEntry.TABLE_PARKINGSPOT, null, values);

        return id;
    }


    public List<Parkingspot> getAllParkingspots(){
        List<Parkingspot> parkingspots = new ArrayList<Parkingspot>();
        String sql = "SELECT * FROM " + ParkingspotContract.ParkingspotEntry.TABLE_PARKINGSPOT + " ORDER BY " + ParkingspotContract.ParkingspotEntry.KEY_IDPARKINGSPOT;

        Cursor cursor = this.db.rawQuery(sql, null);
        if(cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Parkingspot parkingspot = new Parkingspot();
                    parkingspot.setIdparkingspot(cursor.getInt(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_IDPARKINGSPOT)));
                    parkingspot.setIduser(cursor.getInt(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_IDUSER)));
                    parkingspot.setAddress(cursor.getString(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_ADDRESS)));
                    parkingspot.setLocation(cursor.getString(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_LOCATION)));
                    parkingspot.setPrice(cursor.getDouble(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_PRICE)));
                    parkingspot.setCoordinatex(cursor.getDouble(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_COORDINATEX)));
                    parkingspot.setCoordinatey(cursor.getDouble(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_COORDINATEY)));

                    parkingspots.add(parkingspot);
                } while (cursor.moveToNext());
            }
        }
        return parkingspots;
    }

    public List<Parkingspot> getAllParkingspotsByUser(long id){
        List<Parkingspot> parkingspots = new ArrayList<Parkingspot>();
        String sql = "SELECT * FROM " + ParkingspotContract.ParkingspotEntry.TABLE_PARKINGSPOT +
                " WHERE " + ParkingspotContract.ParkingspotEntry.KEY_IDUSER + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Parkingspot parkingspot = new Parkingspot();
                    parkingspot.setIdparkingspot(cursor.getInt(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_IDPARKINGSPOT)));
                    parkingspot.setIduser(cursor.getInt(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_IDUSER)));
                    parkingspot.setAddress(cursor.getString(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_ADDRESS)));
                    parkingspot.setLocation(cursor.getString(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_LOCATION)));
                    parkingspot.setPrice(cursor.getDouble(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_PRICE)));
                    parkingspot.setCoordinatex(cursor.getDouble(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_COORDINATEX)));
                    parkingspot.setCoordinatey(cursor.getDouble(cursor.getColumnIndex(ParkingspotContract.ParkingspotEntry.KEY_COORDINATEY)));

                    parkingspots.add(parkingspot);
                } while (cursor.moveToNext());
            }
        }
        return parkingspots;
    }

    /**
     * Update a Parkingspot
     */
    public int updateParkingspot(Parkingspot parkingspot){
        ContentValues values = new ContentValues();
        values.put(ParkingspotContract.ParkingspotEntry.KEY_IDUSER, parkingspot.getIduser());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_ADDRESS, parkingspot.getAddress());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_LOCATION, parkingspot.getLocation());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_PRICE, parkingspot.getPrice());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_COORDINATEX, parkingspot.getCoordinatex());
        values.put(ParkingspotContract.ParkingspotEntry.KEY_COORDINATEY, parkingspot.getCoordinatey());

        return this.db.update(ParkingspotContract.ParkingspotEntry.TABLE_PARKINGSPOT, values, ParkingspotContract.ParkingspotEntry.KEY_IDPARKINGSPOT + " = ?",
                new String[] { String.valueOf(parkingspot.getIdparkingspot())});
    }

    /**
     * Delete a Parkingspot
     */
    public void deleteParkingspot(long id){

        ReservationDataSource res = new ReservationDataSource(context);
        List<Reservation> reservations = res.getAllReservationsByParkingspotId(id);

        for (Reservation reservation : reservations){
            if(reservation.getIdparkingspot() == id) {
                res.deleteReservation(reservation.getIdreservation());
            }
        }

        this.db.delete(ParkingspotContract.ParkingspotEntry.TABLE_PARKINGSPOT, ParkingspotContract.ParkingspotEntry.KEY_IDPARKINGSPOT + " = ?",
                new String[] { String.valueOf(id)});
    }
}
