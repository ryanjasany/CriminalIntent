package com.example.ryan.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ryan.criminalintent.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ryan on 10/11/2017.
 */

public class CrimeLab {
    private static CrimeLab sCrimbLab;


    private List<Crime> mCrimes;
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
        mCrimes = new ArrayList<>();
    }

    public List<Crime> getCrimes(){

        return mCrimes;

    }

    public Crime getCrime(UUID id){

        for(Crime crime: mCrimes){

            if(crime.getId().equals(id)){
                return crime;

            }

        }
        return null;
    }

    public void addCrime(Crime c)
    {
        mCrimes.add(c);

    }

}

