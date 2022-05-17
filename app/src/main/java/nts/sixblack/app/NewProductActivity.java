package nts.sixblack.app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import lombok.SneakyThrows;
import nts.sixblack.app.database.ListProduct;
import nts.sixblack.app.database.ProductDatabase;
import nts.sixblack.app.model.Product;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class NewProductActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    private EditText edtName;
    private EditText edtPrice;
    private EditText edtQuantity;
    private Button btnChooseImage;
    private Button btnAdd;
    private ImageView imageChoose;
    private Uri mUri;

    private ActivityResultLauncher<Intent> mActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if (data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(NewProductActivity.this.getContentResolver(), uri);
                            imageChoose.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        onInit();

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("note","choose image");
                selectImage();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View view) {
                add();
            }
        });
    }



    private void onInit() {
        edtName = (EditText) findViewById(R.id.edtNameNewProduct);
        edtPrice = (EditText) findViewById(R.id.edtPriceNewProduct);
        edtQuantity = (EditText) findViewById(R.id.edtQuantityNewProduct);
        btnChooseImage = (Button) findViewById(R.id.btnChooseImage);
        btnAdd = (Button) findViewById(R.id.btnAddNewProduct);
        imageChoose = (ImageView) findViewById(R.id.imgShow);
    }

    private void selectImage(){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGalary();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGalary();
        } else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    private void openGalary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.launch(Intent.createChooser(intent, "Choose Image"));
    }

    private void add() throws IOException {
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        String quantity = edtQuantity.getText().toString();



        if (name.isEmpty()||price.isEmpty()||quantity.isEmpty()||mUri==null){
            Toast.makeText(this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            InputStream iStream =   getContentResolver().openInputStream(mUri);
            byte[] image = getBytes(iStream);
            Product product = new Product(name, image, Integer.parseInt(price), Integer.parseInt(quantity));
            ProductDatabase.getInstance(this).getProductDao().insert(product);
            ListProduct.list.add(product);
            Log.e("success","insert product success");
            finish();
        }

    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


}