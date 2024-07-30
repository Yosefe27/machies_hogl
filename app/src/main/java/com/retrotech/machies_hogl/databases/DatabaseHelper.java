package com.retrotech.machies_hogl.databases;



import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.retrotech.machies_hogl.models.PaymentOptions;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    //STRING KEYS FOR TABLE NAMES
    private static final String KEY_LID = "id";
    public static final String TABLE_PAYMENT_OPTIONS = "tbl_payment_options";
    public static final String KEY_PAYMENT_OPTION_ID = "payment_option_id";
    public static final String KEY_PAYMENT_OPTION_NAME = "payment_option_name";
    public static final String KEY_PAYMENT_OPTION_IMAGE = "payment_option_image";


    //STRING KEYS FOR DATABASE DETAILS
    public static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "WEeLedger";

    // private ArrayList<Users> listUsers;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    DatabaseHelper dbHelper;

    @Override
    public void onCreate(SQLiteDatabase db) {


        //TABLE TO HOLD ALL USER DETAILS APART FROM THE MARKETEERS
        String CREATE_PAYMENT_OPTION_TABLE = "CREATE TABLE " + TABLE_PAYMENT_OPTIONS + "("
                + KEY_LID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_PAYMENT_OPTION_ID + " INTEGER,"
                + KEY_PAYMENT_OPTION_NAME + " TEXT,"
                + KEY_PAYMENT_OPTION_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_PAYMENT_OPTION_TABLE);
        String PAYMENT_INDEX = "CREATE UNIQUE INDEX payments_index ON "
                + TABLE_PAYMENT_OPTIONS + " (payment_option_id)";
        db.execSQL(PAYMENT_INDEX);

       


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT_OPTIONS);


        // Create tables again
        onCreate(db);
    }




    /***********************************************************************************************************************
     *                      INSERT FUNCTIONS
     ***********************************************************************************************************************/

  

    public void insertPaymentOptions(ArrayList<PaymentOptions> listPaymentOptions) {
        SQLiteDatabase database = this.getWritableDatabase();

        //create a SQL prepared statement
        String sql = "INSERT OR REPLACE INTO " + TABLE_PAYMENT_OPTIONS + " VALUES(?,?,?,?);";

        //compile the statement and start a transaction
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransactionNonExclusive();

        for (int i = 0; i < listPaymentOptions.size(); i++) {

            PaymentOptions currentPaymentOptions = listPaymentOptions.get(i);

            //for a given column index, simply bind the data to be put inside that index
            statement.bindLong(2, currentPaymentOptions.getPayment_option_id());
            statement.bindString(3, currentPaymentOptions.getPayment_option_name());
            statement.bindString(4, currentPaymentOptions.getPayment_option_image());

            statement.execute();
            statement.clearBindings();
        }
        //set the transaction as successful and end the transaction
//        Log.d("LOG_TAG", "inserting PaymentOptions " + listPaymentOptions.size());// + new Date(System.currentTimeMillis()));
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
    }

   
 
    /***********************************************************************************************************************
     *                      GET FUNCTIONS
     ***********************************************************************************************************************/

    @SuppressLint("Range")
    public ArrayList<PaymentOptions> getAllPaymentOptions() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<PaymentOptions> listPaymentOptions = new ArrayList<>();
        String Query = "select * from tbl_payment_options ";

        Cursor cursor = database.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                PaymentOptions currentPaymentOptions = new PaymentOptions();
                currentPaymentOptions.setPayment_option_id(cursor.getInt(cursor.getColumnIndex(KEY_PAYMENT_OPTION_ID)));
                currentPaymentOptions.setPayment_option_name(cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_OPTION_NAME)));
                currentPaymentOptions.setPayment_option_image(cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_OPTION_IMAGE)));
                //add the customer to the list of customer objects which we plan to return
                listPaymentOptions.add(currentPaymentOptions);
            } while (cursor.moveToNext());
        }
//        Log.d("LOG_TAG", listPaymentOptions.size() + " PaymentOptions downloaded ");
        return listPaymentOptions;

    }

   
    /***********************************************************************************************************************
     *                      CLEAR TABLE DATA FUNCTION
     ***********************************************************************************************************************/
    public void clearTable(String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + table);
    }

    /***********************************************************************************************************************
     *                      COPY TABLE DATA FUNCTION TO ANOTHER TABLE
     ***********************************************************************************************************************/
    public void copyTableData(String table1, String table2) {

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("INSERT INTO " + table1 + " SELECT * FROM " + table2);

    }

}
