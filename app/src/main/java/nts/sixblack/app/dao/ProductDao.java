package nts.sixblack.app.dao;

import androidx.room.*;
import nts.sixblack.app.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    public void insert(Product product);

    @Update
    public void update(Product product);

    @Delete
    public void delete(Product product);

    @Query("select * from product")
    public List<Product> getAll();
}
