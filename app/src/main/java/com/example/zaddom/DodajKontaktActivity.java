package com.example.zaddom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zaddom.kontakt.KontaktContent;

import java.util.Calendar;

import static com.example.zaddom.kontakt.KontaktContent.ITEMS;

public class DodajKontaktActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_kontakt);
        Button dodajKontaktButton = findViewById(R.id.dodajbutton);
        dodajKontaktButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DodajKontakt();
            }
        });
    }

    private void DodajKontakt(){
        EditText editImie = findViewById(R.id.imieeditText);
        EditText editNazwisko = findViewById(R.id.nazwiskoeditText);
        EditText editData = findViewById(R.id.dataeditText);
        EditText editNumer = findViewById(R.id.numereditText);

        String mImie = editImie.getText().toString();
        String mNazwisko = editNazwisko.getText().toString();
        String mData = editData.getText().toString();
        String mNumer = editNumer.getText().toString();
        int nr_zdjecia = (int)(Math.random() * ((16 - 1) + 1)) + 1;

        //Sprawdzenie poprawnosci danych
        if(mImie.isEmpty()){
            editImie.setError(getString(R.string.puste_pole));
            return;
        }
        if(mNazwisko.isEmpty()){
            editNazwisko.setError(getString(R.string.puste_pole));
            return;
        }
        if(mData.isEmpty()){
            editData.setError(getString(R.string.puste_pole));
            return;
        }
        else{
            if(!mData.matches("([0-9]{2})/([0-9]){2}/([0-9]){4}")){
                editData.setError(getString(R.string.format_data));
                return;
            }

            String day = Character.toString(mData.charAt(0)) + Character.toString(mData.charAt(1));
            if(Integer.parseInt(day) > 31){
                editData.setError(getString(R.string.za_duza_wartosc));
                return;
            }
            String month = Character.toString(mData.charAt(3)) + Character.toString(mData.charAt(4));
            if(Integer.parseInt(month) > 12){
                editData.setError(getString(R.string.za_duza_wartosc));
                return;
            }
        }

        if(mNumer.isEmpty()){
            editNumer.setError(getString(R.string.puste_pole));
            return;
        }
        else{
            if(!mNumer.matches("([0-9]){9}")){
                editNumer.setError(getString(R.string.format_numer));
                return;
            }
        }

        Calendar c = Calendar.getInstance();
        int biezacy_rok = c.get(Calendar.YEAR);
        String year = Character.toString(mData.charAt(6)) + Character.toString(mData.charAt(7)) + Character.toString(mData.charAt(8)) + Character.toString(mData.charAt(9));
        if(Integer.parseInt(year) > biezacy_rok){
            editData.setError(getString(R.string.za_duza_wartosc));
            return;
        }

        ITEMS.add(new KontaktContent.Kontakt(String.valueOf(ITEMS.size() + 1), mImie, mNazwisko, mData, mNumer, nr_zdjecia));

        editImie.setText("");
        editNazwisko.setText("");
        editData.setText("");
        editNumer.setText("");

        finish();
    }
}