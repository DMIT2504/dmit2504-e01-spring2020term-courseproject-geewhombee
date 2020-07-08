package ca.nait.dmit2504.finalprojectnm;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "pets",
        foreignKeys = @ForeignKey(entity = People.class,
                                    parentColumns = "uid",
                                    childColumns = "ownerId",
                                    onDelete = CASCADE),
        indices = {@Index("ownerId")})
public class Pets {
    @PrimaryKey(autoGenerate = true)
    public int uid;


    @ColumnInfo(name = "petName")
    public String petName;

    @ColumnInfo(name = "ownerId")
    public int ownerId;

    public Pets(final int uid, String petName, final int ownerId) {
        this.uid = uid;
        this.petName = petName;
        this.ownerId = ownerId;
    }
    @Ignore
    public Pets(String petName, int ownerId) {
        this.petName = petName;
        this.ownerId = ownerId;
    }


    public int getId() { return uid; }

    public String getPetName() { return this.petName; }

    public int getOwnerId() { return this.ownerId; }


}
