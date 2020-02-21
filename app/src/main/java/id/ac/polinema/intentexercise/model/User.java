package id.ac.polinema.intentexercise.model;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String fullname;
    private String email;
    private String password;
    private String confirmPassword;
    private String homepage;
    private String aboutyou;
    private Bitmap profile;

    public Bitmap getProfile() {
        return profile;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }


    public User(String fullname, String email, String homepage, String aboutyou) {
        this.fullname = fullname;
        this.email = email;
        //this.password = password;
        //this.confirmPassword = confirmPassword;
        this.homepage = homepage;
        this.aboutyou = aboutyou;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAboutyou() {
        return aboutyou;
    }

    public void setAboutyou(String aboutyou) {
        this.aboutyou = aboutyou;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullname);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.confirmPassword);
        dest.writeString(this.homepage);
        dest.writeString(this.aboutyou);
    }

    protected User(Parcel in) {
        this.fullname = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.confirmPassword = in.readString();
        this.homepage = in.readString();
        this.aboutyou = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
