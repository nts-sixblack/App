package nts.sixblack.app.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import nts.sixblack.app.dao.CartDao;
import nts.sixblack.app.dao.ProductDao;
import nts.sixblack.app.model.Cart;
import nts.sixblack.app.model.Product;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {
    private static final String db = "nts.cart";
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CartDatabase.class, db)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract CartDao getCartDao();
}
