package nts.sixblack.app.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import nts.sixblack.app.dao.ProductDao;
import nts.sixblack.app.model.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static final String db = "nts.product";
    private static ProductDatabase instance;

    public static synchronized ProductDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class, db)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract ProductDao getProductDao();
}
