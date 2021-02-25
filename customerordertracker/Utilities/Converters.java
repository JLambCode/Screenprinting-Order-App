package com.example.customerordertracker.Utilities;

import java.util.Date;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Date toDate(Long t){ return t == null ? null : new Date(t); }

    @TypeConverter
    public static Long toTimestamp(Date d){
        return d == null ? null : d.getTime();
    }
}
