package nts.sixblack.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import nts.sixblack.app.R;
import nts.sixblack.app.database.CartDatabase;
import nts.sixblack.app.database.UserDetail;
import nts.sixblack.app.model.Cart;
import nts.sixblack.app.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> list;
    private Context context;

    public ProductAdapter(Context context, List<Product> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtPrice.setText(list.get(position).getPrice()+"");
        byte[] bytes = list.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        holder.imageView.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private CardView cardView;
        private ImageView imageView;
        private TextView txtName;
        private TextView txtPrice;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productImageItem);
            txtName = itemView.findViewById(R.id.productNameItem);
            txtPrice = itemView.findViewById(R.id.productPriceItem);
            cardView = itemView.findViewById(R.id.cardViewProductItem);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Add To Cart");
            contextMenu.add(getAdapterPosition(), 101, 0, "Add");
        }
    }

    public void addToCart(int position){
        Cart cart = new Cart();
        Product product = list.get(position);
        cart.setEmail(UserDetail.email);
        cart.setUserName(UserDetail.name);
        cart.setProductName(product.getName());
        cart.setPrice(product.getPrice());
        cart.setImage(product.getImage());

        CartDatabase.getInstance(context).getCartDao().insert(cart);
        Log.e("success","add cart success");
    }
}
