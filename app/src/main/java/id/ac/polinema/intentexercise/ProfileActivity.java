package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import id.ac.polinema.intentexercise.model.User;

public class ProfileActivity extends AppCompatActivity {

    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;
    private ImageView profilepict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullnameText = findViewById(R.id.label_fullname);
        emailText = findViewById(R.id.label_email);
        homepageText = findViewById(R.id.label_homepage);
        aboutText = findViewById(R.id.label_about);
        profilepict = findViewById(R.id.image_profile);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            String usernameInput
            // TODO: display value here
            User user = extras.getParcelable(RegisterActivity.USER_KEY);

            fullnameText.setText(user.getFullname());
            emailText.setText(user.getEmail());
            homepageText.setText(user.getHomepage());
            aboutText.setText(user.getAboutyou());
            profilepict.setImageBitmap(user.getProfile());
            Bitmap bmp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("yourImage"), 0, getIntent().getByteArrayExtra("yourImage").length);
            profilepict.setImageBitmap(bmp);


        }

    }
}
