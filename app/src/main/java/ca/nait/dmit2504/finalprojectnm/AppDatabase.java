package ca.nait.dmit2504.finalprojectnm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {People.class}, version = 3, exportSchema = false)//add exportSchema to stop a debug warning
public abstract class AppDatabase extends RoomDatabase {

    public abstract PeopleDao peopleDao();

    private static volatile AppDatabase appDatabase;
    //
    static AppDatabase getDatabase(final Context context) {
        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "user_database")
                            .allowMainThreadQueries() //this allows database usage on the Main thread. This is not best practise, and should be replaced.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return appDatabase;
    }
}
