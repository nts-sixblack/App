package nts.sixblack.app;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import nts.sixblack.app.database.UserDatabase;
import nts.sixblack.app.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        onInit();

    }
    private void onInit() {
        edtName = (EditText) findViewById(R.id.nameRegister);
        edtEmail = (EditText) findViewById(R.id.emailRegister);
        edtPassword = (EditText) findViewById(R.id.passwordRegister);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
    }

    public void signIn(View view){
        finish();
    }

    public void register(View view){
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        User user = new User(email, name, password);
        UserDatabase.getInstance(this).getUserDao().insert(user);
        Log.e("register","register success");
        finish();
    }
}