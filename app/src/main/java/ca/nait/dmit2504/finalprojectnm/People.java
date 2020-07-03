package ca.nait.dmit2504.finalprojectnm;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "people")
public class People {
    @NonNull
    public int getId() { return uid; }
    @NonNull
    public String getFirstName() { return this.firstName; }
    @NonNull
    public String getLastName() { return this.lastName; }
    @NonNull String getFullName() { return  this.firstName + " " + this.lastName; }


    @PrimaryKey
    @NonNull
    public int uid;

    @NonNull
    @ColumnInfo(name = "firstName")
    public String firstName;

    @NonNull
    @ColumnInfo(name = "lastName")
    public String lastName;

    public People(int uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
