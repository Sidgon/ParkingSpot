package database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import database.DbHelper;
import database.UserContract;
import database.object.Parkingspot;
import database.object.Reservation;
import database.object.User;

/**
 * Created on 22.04.2017.
 */

public class UserDataSource {
    private SQLiteDatabase db;
    private Context context;

    public UserDataSource(Context context){
        DbHelper dbHelper = DbHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Register a new User
     */
    public long createUser(User user){
        long id;
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.KEY_LOGIN, user.getLogin());
        values.put(UserContract.UserEntry.KEY_FIRSTNAME, user.getFirstname());
        values.put(UserContract.UserEntry.KEY_LASTNAME, user.getLastname());
        values.put(UserContract.UserEntry.KEY_ADDRESS, user.getAddress());
        values.put(UserContract.UserEntry.KEY_EMAIL, user.getEmail());
        values.put(UserContract.UserEntry.KEY_PHONE, user.getPhone());
        values.put(UserContract.UserEntry.KEY_PASSWORD, user.getPassword());

        id = this.db.insert(UserContract.UserEntry.TABLE_USER, null, values);

        return id;
    }

    public User getUserById(long id){
        String sql = "SELECT * FROM " + UserContract.UserEntry.TABLE_USER +
                " WHERE " + UserContract.UserEntry.KEY_IDUSER + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        User user = new User();
        if(cursor.getCount() > 0) {
        user.setIduser(cursor.getInt(cursor.getColumnIndex(UserContract.UserEntry.KEY_IDUSER)));
        user.setLogin(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_LOGIN)));
        user.setFirstname(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_FIRSTNAME)));
        user.setLastname(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_LASTNAME)));
        user.setAddress(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_ADDRESS)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_EMAIL)));
        user.setPhone(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_PHONE)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_PASSWORD)));
        }

        return user;

    }

    public User getUserByLogin(String login){
        String sql = "SELECT * FROM " + UserContract.UserEntry.TABLE_USER +
                " WHERE " + UserContract.UserEntry.KEY_LOGIN + " = " +"'"+ login +"'"+ " COLLATE NOCASE";

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        User user = new User();
        if(cursor.getCount() > 0) {
            user.setIduser(cursor.getInt(cursor.getColumnIndex(UserContract.UserEntry.KEY_IDUSER)));
            user.setLogin(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_LOGIN)));
            user.setFirstname(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_FIRSTNAME)));
            user.setLastname(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_LASTNAME)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_ADDRESS)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_PHONE)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_PASSWORD)));
        }

        return user;

    }

    /*
     * Get all Users
     */

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM " + UserContract.UserEntry.TABLE_USER;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.getCount() > 0) {
            if(cursor.moveToFirst()){
                do{
                    User user = new User();
                    user.setIduser(cursor.getInt(cursor.getColumnIndex(UserContract.UserEntry.KEY_IDUSER)));
                    user.setLogin(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_LOGIN)));
                    user.setFirstname(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_FIRSTNAME)));
                    user.setLastname(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_LASTNAME)));
                    user.setAddress(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_ADDRESS)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_EMAIL)));
                    user.setPhone(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_PHONE)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.KEY_PASSWORD)));

                    users.add(user);
                }while(cursor.moveToNext());
            }
        }

        return users;
    }

    /*
     * Update a User
     */
    public int updateUser(User user){
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.KEY_LOGIN, user.getLogin());
        values.put(UserContract.UserEntry.KEY_FIRSTNAME, user.getFirstname());
        values.put(UserContract.UserEntry.KEY_LASTNAME, user.getLastname());
        values.put(UserContract.UserEntry.KEY_ADDRESS, user.getAddress());
        values.put(UserContract.UserEntry.KEY_EMAIL, user.getEmail());
        values.put(UserContract.UserEntry.KEY_PHONE, user.getPhone());
        values.put(UserContract.UserEntry.KEY_PASSWORD, user.getPassword());

        return this.db.update(UserContract.UserEntry.TABLE_USER, values, UserContract.UserEntry.KEY_IDUSER + " = ?",
                new String[] { String.valueOf(user.getIduser())});
    }

    /*
     * Delete a User - it will also delete all parking spots and rents
     */
    public void deleteUser(long id){

        ParkingspotDataSource par = new ParkingspotDataSource(context);
        ReservationDataSource res = new ReservationDataSource(context);
        List<Parkingspot> parkingspots = par.getAllParkingspotsByUser(id);
        List<Reservation> reservations = res.getAllReservationsWithUser(id);

        for(Parkingspot parkingspot : parkingspots){
            if(parkingspot.getIduser() == id) {
                par.deleteParkingspot(parkingspot.getIdparkingspot());
            }
        }

        for (Reservation reservation : reservations){
            if(reservation.getIdprovider() == id || reservation.getIdtenant() == id) {
                res.deleteReservation(reservation.getIdreservation());
            }
        }

        //delete the User
        this.db.delete(UserContract.UserEntry.TABLE_USER, UserContract.UserEntry.KEY_IDUSER + " = ?",
                new String[] { String.valueOf(id)});

    }
}
