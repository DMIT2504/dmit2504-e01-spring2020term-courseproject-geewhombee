package ca.nait.dmit2504.finalprojectnm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PeopleDao {
    @Query("SELECT * FROM people")
    List<People> getAll();

    @Query("SELECT * FROM people WHERE uid IN (:userIds)")
    List<People> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM people WHERE firstName LIKE :first AND " +
            "lastName LIKE :last LIMIT 1")
    People findByName(String first, String last);

    @Query("SELECT * FROM people WHERE uid LIKE :id")
    People findById(int id);
    @Update
    public void updatePerson(People people);

    @Insert
    void insertAll(People... people);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long singlePersonInsert(People people);

    @Delete
    void delete(People people);

    //Pet queries
    @Query("SELECT * FROM pets WHERE ownerId LIKE :id")
    List<Pets> getPetsByOwnerId(int id);
    @Query("SELECT * FROM pets")
    List<Pets> getAllPets();

}
