package database;

import android.provider.BaseColumns;

/**
 * Created on 22.04.2017.
 */

public final class UserContract {

    private UserContract(){
        //empty constructor should never be instantiated
    }

    public static abstract class UserEntry implements BaseColumns{
        //Table Name
        public static final String TABLE_USER = "user";

        //User Column names
        public static final String KEY_IDUSER = "iduser";
        public static final String KEY_LOGIN = "login";
        public static final String KEY_FIRSTNAME = "firstname";
        public static final String KEY_LASTNAME = "lastname";
        public static final String KEY_ADDRESS = "address";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_PASSWORD = "password";

        //Database creation sql statement
        public static final String CREATE_TABLE_USER = "CREATE TABLE "
                + TABLE_USER + "(" + KEY_IDUSER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_LOGIN + " TEXT NOT NULL, "
                + KEY_FIRSTNAME + " TEXT NOT NULL, "
                + KEY_LASTNAME + " TEXT NOT NULL, "
                + KEY_ADDRESS + " TEXT NOT NULL, "
                + KEY_EMAIL + " TEXT NOT NULL, "
                + KEY_PHONE + " TEXT NOT NULL, "
                + KEY_PASSWORD + " TEXT NOT NULL);";

    }
}