package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.ac.polinema.intentexercise.model.User;

public class ProfileActivity extends AppCompatActivity {

    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;
    private ImageView profilepict;

    private User user;

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
            user = extras.getParcelable(RegisterActivity.USER_KEY);

            fullnameText.setText(user.getFullname());
            emailText.setText(user.getEmail());
            homepageText.setText(user.getHomepage());
            aboutText.setText(user.getAboutyou());
            profilepict.setImageBitmap(user.getProfile());
            Bitmap bmp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("yourImage"), 0, getIntent().getByteArrayExtra("yourImage").length);
            profilepict.setImageBitmap(bmp);


        }

    }


    public void handleHomepage(View view) {
        String homepage = homepageText.getText().toString();


        if (!homepage.startsWith("https://") && !homepage.startsWith("http://")){
            homepage = "http://" + homepage;
        }
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(homepage));
        if (openUrlIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openUrlIntent);
        }
    }
}
