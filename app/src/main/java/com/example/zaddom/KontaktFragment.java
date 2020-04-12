package com.example.zaddom;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zaddom.kontakt.KontaktContent;

public class KontaktFragment extends Fragment {
    private MyKontaktRecyclerViewAdapter mRecyclerViewAdapter;

    private OnListFragmentInteractionListener mListener;

    public KontaktFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kontakt_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mRecyclerViewAdapter = new MyKontaktRecyclerViewAdapter(KontaktContent.ITEMS, mListener);
            recyclerView.setAdapter(mRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        notifyDataChange();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentClickInteraction(KontaktContent.Kontakt kontakt, int position);
        void onListFragmentLongClickInteraction(KontaktContent.Kontakt kontakt, int position);
        void onDeleteButtonClickInteraction(int position);
    }

    public void notifyDataChange(){
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
}