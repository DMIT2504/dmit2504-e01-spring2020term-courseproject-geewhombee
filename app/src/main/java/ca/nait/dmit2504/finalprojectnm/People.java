package ca.nait.dmit2504.finalprojectnm;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "people")
public class People {

    @PrimaryKey(autoGenerate = true)
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
    @Ignore
    public People(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    public int getId() { return uid; }
    @NonNull
    public String getFirstName() { return this.firstName; }
    @NonNull
    public String getLastName() { return this.lastName; }
    @NonNull
    public String getFullName() { return  this.firstName + " " + this.lastName; }
}
