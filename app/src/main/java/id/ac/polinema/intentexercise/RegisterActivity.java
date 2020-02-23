package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Domain;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import id.ac.polinema.intentexercise.model.User;

public class RegisterActivity extends AppCompatActivity
        implements Validator.ValidationListener{

    Validator validator;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    public static String USER_KEY="USER_KEY";

    @NotEmpty
    private TextInputEditText fullnameInput;

    @NotEmpty
    @Email
    private TextInputEditText emailInput;

    @NotEmpty
    @Password
    private TextInputEditText passwordInput;

    @NotEmpty
    @ConfirmPassword
    private TextInputEditText confirmpasswordInput;

    @NotEmpty
    @Domain
    private TextInputEditText homepageInput;

    @NotEmpty
    private TextInputEditText aboutInput;

    //foto
    Bitmap bitmap;
    private ImageView ImageInput;
    //Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput =findViewById(R.id.text_fullname);
        emailInput =findViewById(R.id.text_email);
        passwordInput= findViewById(R.id.text_password);
        confirmpasswordInput = findViewById(R.id.text_confirm_password);
        homepageInput = findViewById(R.id.text_homepage);
        aboutInput = findViewById(R.id.text_about);
        ImageInput = findViewById(R.id.image_profile);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    ImageInput.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }


    public void handleOk(View view) {
        validator.validate();

    }

    public void handleProfile(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onValidationSucceeded() {
            String fullname = fullnameInput.getText().toString();
            String email = emailInput.getText().toString();
            String homepage = homepageInput.getText().toString();
            String about = aboutInput.getText().toString();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);

            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(USER_KEY, new User(fullname, email, homepage, about));

            //galeri
            intent.putExtra("yourImage", baos.toByteArray());
            startActivity(intent);

            //Toast.makeText(this, "Validasi berhasil", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
        }
        if(errors.size() > 0){
            if (errors.get(0).getView() instanceof EditText) {
                errors.get(0).getView().requestFocus();
            }
            else {
                String message = errors.get(0).getCollatedErrorMessage(this);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }



}
