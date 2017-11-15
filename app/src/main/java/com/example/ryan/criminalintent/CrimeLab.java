package com.example.ryan.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ryan.criminalintent.database.CrimeBaseHelper;
import com.example.ryan.criminalintent.database.CrimeCursorWrapper;
import com.example.ryan.criminalintent.database.CrimeDbSchema;
import com.example.ryan.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ryan on 10/11/2017.
 */

public class CrimeLab {
    private static CrimeLab sCrimbLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static CrimeLab get(Context context){

        if(sCrimbLab == null){

            sCrimbLab = new CrimeLab(context);
        }
        return sCrimbLab;

    }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes(){

        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();

            }
            }finally{

            cursor.close();

        }

        return crimes;

    }

    public Crime getCrime(UUID id){


        return null;
    }

    public void updateCrime(Crime crime){

        String uuidString = crime.getId().toString();

        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?", new String[]{ uuidString});

    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;

    }

    public void addCrime(Crime c)
    {
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeTable.NAME, null, values);


    }

}

