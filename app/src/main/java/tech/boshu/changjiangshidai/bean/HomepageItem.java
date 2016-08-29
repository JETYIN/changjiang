package tech.boshu.changjiangshidai.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zoulinlin on 16/1/2.
 */
public class HomepageItem implements Parcelable {
    public int key;
    public String name;
    public String imageName;
    public String jumptoClass;
    public int status = 0;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.key);
        dest.writeString(this.name);
        dest.writeString(this.imageName);
        dest.writeString(this.jumptoClass);
        dest.writeInt(this.status);
    }

    public HomepageItem() {
    }

    protected HomepageItem(Parcel in) {
        this.key = in.readInt();
        this.name = in.readString();
        this.imageName = in.readString();
        this.jumptoClass = in.readString();
        this.status = in.readInt();
    }

    public static final Parcelable.Creator<HomepageItem> CREATOR = new Parcelable
            .Creator<HomepageItem>() {
        public HomepageItem createFromParcel(Parcel source) {
            return new HomepageItem(source);
        }

        public HomepageItem[] newArray(int size) {
            return new HomepageItem[size];
        }
    };

}
