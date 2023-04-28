package edu.samsungit.remsely.proformula.data.data_sources.room.entites;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Map;

@Entity(tableName = "Pilots")
public class PilotsEntity {
    @PrimaryKey
    @NonNull
    public String pilotKey;
    public String flag;
    public String name;

    public PilotsEntity(String key, String flag, String name){
        this.pilotKey = key;
        this.flag = flag;
        this.name = name;
    }
}
