package nts.sixblack.app.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nts.sixblack.app.R;
import nts.sixblack.app.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{
    private List<Cart> list;

    public CartAdapter(List<Cart> list){
        this.list = list;
    }

    @NonNull
    @Override
    public CartAdapter.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartHolder holder, int position) {
        Cart cart = list.get(position);

        byte[] bytes = cart.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        holder.imageView.setImageBitmap(bitmap);
        holder.txtNameUser.setText(cart.getUserName());
        holder.txtNameProduct.setText(cart.getProductName());
        holder.txtPrice.setText(cart.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CartHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtNameUser;
        TextView txtNameProduct;
        TextView txtPrice;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgCartItem);
            txtNameUser = itemView.findViewById(R.id.txtNameUserCartItem);
            txtNameProduct = itemView.findViewById(R.id.txtNameProductCartItem);
            txtPrice = itemView.findViewById(R.id.txtPriceProductCartItem);

        }
    }
}
