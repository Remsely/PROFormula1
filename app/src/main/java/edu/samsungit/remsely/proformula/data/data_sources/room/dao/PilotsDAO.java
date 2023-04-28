package edu.samsungit.remsely.proformula.data.data_sources.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import edu.samsungit.remsely.proformula.data.data_sources.room.entites.PilotsEntity;

@Dao
public interface PilotsDAO {
    @Insert
    void insert(PilotsEntity pilots);

    @Update
    void update(PilotsEntity pilots);

    @Delete
    void delete(PilotsEntity pilots);

    @Query("SELECT * FROM Pilots WHERE pilotKey = :key")
    PilotsEntity getPilotByKey(String key);
}
