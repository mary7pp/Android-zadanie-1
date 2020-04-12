package com.example.zaddom;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zaddom.KontaktFragment.OnListFragmentInteractionListener;
import com.example.zaddom.kontakt.KontaktContent.Kontakt;

import java.util.List;

public class MyKontaktRecyclerViewAdapter extends RecyclerView.Adapter<MyKontaktRecyclerViewAdapter.ViewHolder> {

    private final List<Kontakt> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyKontaktRecyclerViewAdapter(List<Kontakt> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_kontakt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Kontakt kontakt = mValues.get(position);
        holder.mItem = kontakt;
        holder.mimieView.setText(kontakt.imie);
        Context context = holder.mView.getContext();
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
            holder.mzdjecieView.setImageDrawable(kontaktDrawable);
        }
        holder.mdeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "del txt", Toast.LENGTH_SHORT).show();
                mListener.onDeleteButtonClickInteraction(position);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                mListener.onListFragmentLongClickInteraction(holder.mItem, position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mzdjecieView;
        public final TextView mimieView;
        public final ImageButton mdeleteButton;
        public Kontakt mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mzdjecieView = view.findViewById(R.id.zdjecieView);
            mimieView = view.findViewById(R.id.imieView);
            mdeleteButton = view.findViewById(R.id.deleteButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mimieView.getText() + "'";
        }
    }
}