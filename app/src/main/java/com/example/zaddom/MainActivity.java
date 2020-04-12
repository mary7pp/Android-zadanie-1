package com.example.zaddom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zaddom.kontakt.KontaktContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements
        KontaktFragment.OnListFragmentInteractionListener,
        CallDialog.OnCallDialogInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener {

    private int currentItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DodajKontaktActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onListFragmentClickInteraction(KontaktContent.Kontakt kontakt, int position) {
        //Toast.makeText(getApplicationContext(), "klikniecie elementu", Toast.LENGTH_SHORT).show();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayKontaktInFragment(kontakt);
        }
        else {
            startSecondActivity(kontakt, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(KontaktContent.Kontakt kontakt, int position) {
        //Toast.makeText(getApplicationContext(), "przytrzymanie elementu", Toast.LENGTH_SHORT).show();
        showCallDialog(kontakt.imie);
    }

    public void showCallDialog(String imie){
        CallDialog.newInstance(imie).show(getSupportFragmentManager(),getString(R.string.call_dialog_tag));
    }

    @Override
    public void onDeleteButtonClickInteraction(int position) {
        showDeleteDialog();
        currentItemPosition = position;
    }

    public static final String kontaktExtra = "KontaktExtra";

    private void startSecondActivity(KontaktContent.Kontakt kontakt, int position){
        Intent intent = new Intent(this,KontaktInfoActivity.class);
        intent.putExtra(kontaktExtra, kontakt);
        startActivity(intent);
    }

    public void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1){
            KontaktContent.removeKontakt(currentItemPosition);
            ((KontaktFragment) getSupportFragmentManager().findFragmentById(R.id.Mainfragment)).notifyDataChange();
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                ((KontaktInfoFragment) getSupportFragmentManager().findFragmentById(R.id.DisplayInfofragment)).usunietyKontakt();
            }
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {}

    private void displayKontaktInFragment(KontaktContent.Kontakt kontakt){
        KontaktInfoFragment kontaktInfoFragment = ((KontaktInfoFragment) getSupportFragmentManager().findFragmentById(R.id.DisplayInfofragment));
        if(kontaktInfoFragment != null){
            kontaktInfoFragment.wyswietlKontakt(kontakt, 1);
        }
    }

    @Override
    public void onCallDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Calling", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallDialogNegativeClick(DialogFragment dialog) {
    }
}