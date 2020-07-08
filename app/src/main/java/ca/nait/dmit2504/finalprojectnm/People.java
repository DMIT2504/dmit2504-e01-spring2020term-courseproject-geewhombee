package ca.nait.dmit2504.finalprojectnm;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "people")
public class People {

    @PrimaryKey(autoGenerate = true)

    public int uid;


    //@ColumnInfo(name = "firstName")
    public String firstName;


    @ColumnInfo(name = "lastName")
    public String lastName;

    public People(int uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Ignore
    public People(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getId() { return uid; }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getFullName() { return  this.firstName + " " + this.lastName; }
}
