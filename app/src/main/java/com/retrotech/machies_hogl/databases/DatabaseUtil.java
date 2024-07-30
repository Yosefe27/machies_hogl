package com.retrotech.machies_hogl.databases;

import static com.retrotech.machies_hogl.databases.DatabaseHelper.DATABASE_NAME;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class DatabaseUtil {


    //______________________________________________________________________________________________

    //todo -> rename the database
    final static String OUR_DATABASE_NAME = DATABASE_NAME;
    final static String FOLDER_EXTERNAL_DIRECTORY = Environment.getExternalStorageDirectory() + "/WEeLedger";

    //______________________________________________________________________________________________

    /**
     * Call this method from any activity in your app (
     * for example ->    DatabaseUtil.copyDatabaseToExtStg(MainActivity.this);
     * this method will copy the database of the application into SDCard folder "`/TollAppDatabase/Database.sqlite" (OUR_DATABASE_NAME)
     */
    public static void copyDatabaseToExtStg(Context ctx) {
        //external storage file
        File externalDirectory = new File(FOLDER_EXTERNAL_DIRECTORY);
        if (!externalDirectory.exists())
            externalDirectory.mkdirs();
        File toFile = new File(externalDirectory, OUR_DATABASE_NAME);
        //internal storage file
        File fromFile = ctx.getDatabasePath(OUR_DATABASE_NAME);
        if (fromFile.exists())
            copy(fromFile, toFile);
    }


    //______________________________________________________________________________________________ Utility function

    /**
     * @param fromFile source location
     * @param toFile   destination location
     *                 copy file from 1 location to another
     */


    static void copy(File fromFile, File toFile)

    {
        try {
            FileInputStream is = new FileInputStream(fromFile);
            FileChannel src = is.getChannel();
            FileOutputStream os = new FileOutputStream(toFile);
            FileChannel dst = os.getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();
            is.close();
            dst.close();
            os.close();
        } catch (Exception e) {
            //todo in case of exception
        }
    }

    /**
     * importDatabase
     * Copies the database file at the specified location over the current
     * internal application database.
     * */

    public static void copyFromExtStgToDatabase(Context ctx) {
        //external storage file
        File externalDirectory = new File(FOLDER_EXTERNAL_DIRECTORY);
        if (!externalDirectory.exists())
            externalDirectory.mkdirs();
        File fromFile = new File(externalDirectory, OUR_DATABASE_NAME);
        //internal storage file
        File toFile = ctx.getDatabasePath(OUR_DATABASE_NAME);
        if (fromFile.exists())
            copy(fromFile, toFile);
    }

}
