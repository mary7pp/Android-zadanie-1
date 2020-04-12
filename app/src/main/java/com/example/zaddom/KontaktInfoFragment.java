package com.example.zaddom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zaddom.kontakt.KontaktContent;

public class KontaktInfoFragment extends Fragment {

    public KontaktInfoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kontakt_info, container, false);
    }

    public void usunietyKontakt(){
        FragmentActivity activity = getActivity();
        TextView imienazwisko = activity.findViewById(R.id.ImieNazwiskotextView);
        ImageView zdjecie = activity.findViewById(R.id.zdjecieimageView);
        TextView numer = activity.findViewById(R.id.NumertextView);
        TextView data = activity.findViewById(R.id.DatatextView);

        imienazwisko.setText("");
        numer.setText("");
        data.setText("");
        zdjecie.setVisibility(View.INVISIBLE);
    }

    public void wyswietlKontakt(KontaktContent.Kontakt kontakt, int widok){
        FragmentActivity activity = getActivity();

        TextView imienazwisko = activity.findViewById(R.id.ImieNazwiskotextView);
        ImageView zdjecie = activity.findViewById(R.id.zdjecieimageView);
        TextView numer = activity.findViewById(R.id.NumertextView);
        TextView data = activity.findViewById(R.id.DatatextView);

        if(widok == 1){
            zdjecie.setVisibility(View.VISIBLE);
        }

        imienazwisko.setText(kontakt.imie + " " + kontakt.nazwisko);
        Context context = getContext();
        if(kontakt.nr_zdjecia > 0 && kontakt.nr_zdjecia <= 16){
            Drawable kontaktDrawable;
            switch (kontakt.nr_zdjecia){
                case 1:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                    break;
                case 2:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                    break;
                case 3:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_3);
                    break;
                case 4:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_4);
                    break;
                case 5:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_5);
                    break;
                case 6:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_6);
                    break;
                case 7:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_7);
                    break;
                case 8:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_8);
                    break;
                case 9:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_9);
                    break;
                case 10:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_10);
                    break;
                case 11:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_11);
                    break;
                case 12:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_12);
                    break;
                case 13:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_13);
                    break;
                case 14:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_14);
                    break;
                case 15:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_15);
                    break;
                case 16:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_16);
                    break;
                default:
                    kontaktDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
            }
            zdjecie.setImageDrawable(kontaktDrawable);
        }
        numer.setText("Phone number: "+kontakt.numer);
        data.setText("Birthday: "+kontakt.data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null){
            KontaktContent.Kontakt otrzymanyKontakt = intent.getParcelableExtra(MainActivity.kontaktExtra);
            if(otrzymanyKontakt != null){
                wyswietlKontakt(otrzymanyKontakt, 0);
            }
        }
    }
}