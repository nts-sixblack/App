package nts.sixblack.app.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import nts.sixblack.app.dao.UserDao;
import nts.sixblack.app.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String db = "nts.user";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, db)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract UserDao getUserDao();
}
