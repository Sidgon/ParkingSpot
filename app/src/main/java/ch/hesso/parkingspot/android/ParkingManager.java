package ch.hesso.parkingspot.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Lavdrim on 27.04.2017.
 */

public class ParkingManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref2";

    // User name (make variable public to access from outside)
    public static final String KEY_IDPARKINGSPOT = "idparkingspot";
    public static final String KEY_IDUSER = "iduser";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_PRICE = "price";
    public static final String KEY_COORDINATEX = "coordinatex";
    public static final String KEY_COORDINATEY = "coordinatey";

    // Constructor
    public ParkingManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */

    public void saveParkingspot(String idParkingspot, String iduser, String address, String location, String price, String coordinatex, String coordinatey){
        editor.putString(KEY_IDPARKINGSPOT, idParkingspot);
        editor.putString(KEY_IDUSER, iduser);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_LOCATION, location);
        editor.putString(KEY_PRICE, price);
        editor.putString(KEY_COORDINATEX, coordinatex);
        editor.putString(KEY_COORDINATEY, coordinatey);

        // commit changes
        editor.commit();
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getParkingDetails(){
        HashMap<String, String> parkingspot = new HashMap<String, String>();
        // parkingspot.android
        parkingspot.put(KEY_IDPARKINGSPOT, pref.getString(KEY_IDPARKINGSPOT, null));
        parkingspot.put(KEY_IDUSER, pref.getString(KEY_IDUSER, null));
        parkingspot.put(KEY_ADDRESS, pref.getString(KEY_ADDRESS, null));
        parkingspot.put(KEY_LOCATION, pref.getString(KEY_LOCATION, null));
        parkingspot.put(KEY_PRICE, pref.getString(KEY_PRICE, null));
        parkingspot.put(KEY_COORDINATEX, pref.getString(KEY_COORDINATEX, null));
        parkingspot.put(KEY_COORDINATEY, pref.getString(KEY_COORDINATEY, null));

        // return user
        return parkingspot;
    }

    public void updateParkingspot(){
        editor.clear();
        editor.commit();
    }

}
