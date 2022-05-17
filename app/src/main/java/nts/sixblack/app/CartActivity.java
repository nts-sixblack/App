package nts.sixblack.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nts.sixblack.app.adapter.CartAdapter;
import nts.sixblack.app.database.CartDatabase;
import nts.sixblack.app.database.UserDetail;
import nts.sixblack.app.model.Cart;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Cart> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        onInit();

        CartAdapter cartAdapter = new CartAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void onInit() {
        recyclerView = findViewById(R.id.cartRecycleView);
        list = CartDatabase.getInstance(this).getCartDao().listCart(UserDetail.email);
    }
}