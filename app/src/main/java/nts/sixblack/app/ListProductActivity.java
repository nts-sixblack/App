package nts.sixblack.app;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nts.sixblack.app.adapter.ProductAdapter;
import nts.sixblack.app.database.ListProduct;
import nts.sixblack.app.database.ProductDatabase;
import nts.sixblack.app.model.Product;

import java.util.List;

public class ListProductActivity extends AppCompatActivity {

    private RecyclerView productRecyclerView;
    private List<Product> list;
    private Button btnNewProduct;
    private Button btnCart;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        onInit();

        productAdapter = new ProductAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        productRecyclerView.setAdapter(productAdapter);
        productRecyclerView.setLayoutManager(layoutManager);

    }

    private void onInit() {
        productRecyclerView = (RecyclerView) findViewById(R.id.listAllProduct);
        list = ProductDatabase.getInstance(this).getProductDao().getAll();
        ListProduct.list = list;
        btnNewProduct = (Button) findViewById(R.id.btnNewProduct);
        btnCart = (Button) findViewById(R.id.btnCart);
    }

    public void newProduct(View view){
        Intent intent = new Intent(this, NewProductActivity.class);
        startActivity(intent);
    }

    public void cart(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = ListProduct.list;
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case 101:
                Toast.makeText(this, "Add to Cart", Toast.LENGTH_SHORT).show();
                productAdapter.addToCart(item.getGroupId());
                return true;
            case 102:
                Toast.makeText(this, "Hmmm", Toast.LENGTH_SHORT).show();
                productAdapter.addToCart(item.getGroupId());
                return true;
        }
        return true;
    }
}