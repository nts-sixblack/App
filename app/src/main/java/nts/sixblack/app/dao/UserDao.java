package nts.sixblack.app.dao;

import androidx.room.*;
import nts.sixblack.app.model.User;

@Dao
public interface UserDao {
    @Insert
    public void insert(User user);

    @Update
    public void update(User user);

    @Delete
    public void delete(User user);

    @Query("select * from user where email = :email and password = :password")
    public User login(String email, String password);

    @Query("select * from user where email = :email")
    public User checkEmail(String email);

}
