package com.example.zaddom.kontakt;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KontaktContent {
    public static final List<Kontakt> ITEMS = new ArrayList<Kontakt>();

    public static final Map<String, Kontakt> ITEM_MAP = new HashMap<String, Kontakt>();

    private static final int COUNT = 1;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Kontakt item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Kontakt createDummyItem(int position) {
        return new Kontakt(String.valueOf(position), "Henryk", "Sienkiewicz", "10/03/2010", "253766840", 1);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static void removeKontakt(int position){
        ITEMS.remove(position);
    }

    public static class Kontakt implements Parcelable {
        public final String id;
        public final String imie;
        public final String nazwisko;
        public final String data;
        public final String numer;
        public final int nr_zdjecia;

        public Kontakt(String id, String imie, String nazwisko, String data, String numer, int nr_zdjecia) {
            this.id = id;
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.data = data;
            this.numer = numer;
            this.nr_zdjecia = nr_zdjecia;
        }

        protected Kontakt(Parcel in) {
            id = in.readString();
            imie = in.readString();
            nazwisko = in.readString();
            data = in.readString();
            numer = in.readString();
            nr_zdjecia = in.readInt();
        }

        public static final Creator<Kontakt> CREATOR = new Creator<Kontakt>() {
            @Override
            public Kontakt createFromParcel(Parcel in) {
                return new Kontakt(in);
            }

            @Override
            public Kontakt[] newArray(int size) {
                return new Kontakt[size];
            }
        };

        @Override
        public String toString() {
            return imie;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(imie);
            dest.writeString(nazwisko);
            dest.writeString(data);
            dest.writeString(numer);
            dest.writeInt(nr_zdjecia);
        }
    }
}