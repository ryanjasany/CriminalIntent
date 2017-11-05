package com.example.ryan.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ryan on 10/11/2017.
 */

public class CrimeLab {
    private static CrimeLab sCrimbLab;

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context){

        if(sCrimbLab == null){

            sCrimbLab = new CrimeLab(context);
        }
        return sCrimbLab;

    }

    private CrimeLab(Context context){
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

