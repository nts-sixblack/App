package nts.sixblack.app.dao;

import androidx.room.*;
import nts.sixblack.app.model.Cart;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    public void insert(Cart cart);

    @Update
    public void update(Cart cart);

    @Delete
    public void delete(Cart cart);

    @Query("select * from cart where email = :email")
    public List<Cart> listCart(String email);
}
