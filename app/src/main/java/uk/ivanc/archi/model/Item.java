package uk.ivanc.archi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    public long Id;
    private String itemName;
    private String categoryName;
    private int price;
    private String description;

    public Item() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.Id);
        dest.writeString(this.itemName);
        dest.writeString(this.categoryName);
        dest.writeInt(this.price);
        dest.writeString(this.description);
    }

    protected Item(Parcel in) {
        this.Id = in.readLong();
        this.itemName = in.readString();
        this.categoryName = in.readString();
        this.price = in.readInt();
        this.description = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        // if hashcode equal
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item that = (Item) o;

        if (Id != that.Id) return false;
        if (itemName != that.itemName) return false;
        if (categoryName != that.categoryName) return false;
        if (price != that.price) return false;
        if (description != that.description) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (Id ^ (Id >>> 32));
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public void show() {
        System.out.println(this.Id);
        System.out.println(this.itemName);
        System.out.println(this.categoryName);
    }
}
