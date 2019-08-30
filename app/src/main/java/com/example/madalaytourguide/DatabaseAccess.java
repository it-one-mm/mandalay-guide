package com.example.madalaytourguide;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     *
     *
     *
     */

    public ArrayList<PlaceModel> getAllPlaces()
    {
        ArrayList<PlaceModel> placeModels=new ArrayList<PlaceModel>();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(PlaceDAO.TBName);

        Cursor cursor=qb.query(database,null,null,null,null,null,PlaceDAO.ColID);

        while (cursor.moveToNext())
            {

            PlaceModel tempobjet=new PlaceModel();
            tempobjet.id=cursor.getInt(cursor.getColumnIndex(PlaceDAO.ColID));
            tempobjet.Name=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColName));
            tempobjet.Address=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColAddress)) ;

            tempobjet.Img1=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg1));
            tempobjet.Img2 = cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg2));
            tempobjet.Img3=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg3));
            tempobjet.Type=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColType));
            tempobjet.Phone=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColPhone));
            tempobjet.Desc=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColDec));
            tempobjet.lat=cursor.getDouble(cursor.getColumnIndex(PlaceDAO.ColLat));
            tempobjet.lon=cursor.getDouble(cursor.getColumnIndex(PlaceDAO.ColLon));
            placeModels.add(tempobjet);
        }

        return placeModels;
    }
    public ArrayList<PlaceModel> getAllPagoda()
    {
        ArrayList<PlaceModel> placeModels=new ArrayList<PlaceModel>();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(PlaceDAO.TBName);

        String selection=PlaceDAO.ColType+"=?";
        String selectionargs[]=new String[]{"Pagoda"};
        Cursor cursor=qb.query(database,null,selection,selectionargs,null,null,PlaceDAO.ColID);

        while (cursor.moveToNext())
        {

            PlaceModel tempobjet=new PlaceModel();
            tempobjet.id=cursor.getInt(cursor.getColumnIndex(PlaceDAO.ColID));
            tempobjet.Name=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColName));
            tempobjet.Address=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColAddress)) ;

            tempobjet.Img1=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg1));
            tempobjet.Img2 = cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg2));
            tempobjet.Img3=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg3));
            tempobjet.Type=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColType));
            tempobjet.Phone=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColPhone));

            tempobjet.Desc=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColDec));
            tempobjet.lat=cursor.getDouble(cursor.getColumnIndex(PlaceDAO.ColLat));
            tempobjet.lon=cursor.getDouble(cursor.getColumnIndex(PlaceDAO.ColLon));
            placeModels.add(tempobjet);
        }

        return placeModels;
    }
    public ArrayList<PlaceModel> getAllRestaurent()
    {
        ArrayList<PlaceModel> placeModels=new ArrayList<PlaceModel>();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(PlaceDAO.TBName);

        String selection=PlaceDAO.ColType+"=?";//Type=
        String selectionargs[]=new String[]{"Restaurent"};
        Cursor cursor=qb.query(database,null,selection,selectionargs,null,null,PlaceDAO.ColID);

        while (cursor.moveToNext())
        {

            PlaceModel tempobjet=new PlaceModel();
            tempobjet.id=cursor.getInt(cursor.getColumnIndex(PlaceDAO.ColID));
            tempobjet.Name=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColName));
            tempobjet.Address=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColAddress)) ;

            tempobjet.Img1=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg1));
            tempobjet.Img2 = cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg2));
            tempobjet.Img3=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColImg3));
            tempobjet.Type=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColType));
            tempobjet.Phone=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColPhone));
            tempobjet.Desc=cursor.getString(cursor.getColumnIndex(PlaceDAO.ColDec));
            tempobjet.lat=cursor.getDouble(cursor.getColumnIndex(PlaceDAO.ColLat));
            tempobjet.lon=cursor.getDouble(cursor.getColumnIndex(PlaceDAO.ColLon));
            placeModels.add(tempobjet);
        }

        return placeModels;
    }

}