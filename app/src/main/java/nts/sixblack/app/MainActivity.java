package nts.sixblack.app;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import nts.sixblack.app.database.UserDatabase;
import nts.sixblack.app.database.UserDetail;
import nts.sixblack.app.model.User;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onInit();

    }

    private void onInit() {
        edtEmail = (EditText) findViewById(R.id.emailLogin);
        edtPassword = (EditText) findViewById(R.id.passwordLogin);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
    }

    public void signIn(View view){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        User user = UserDatabase.getInstance(this).getUserDao().login(email, password);
        if (user!=null){
            Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
            UserDetail.email = user.getEmail();
            UserDetail.name = user.getName();
            Intent intent = new Intent(this, ListProductActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "login error", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}