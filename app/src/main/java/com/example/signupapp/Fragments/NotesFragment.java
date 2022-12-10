package com.example.signupapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.signupapp.AddNotesActivity;
import com.example.signupapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.checkerframework.checker.nullness.qual.NonNull;


public class NotesFragment extends Fragment {
    FloatingActionButton saveNotes;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View f = inflater.inflate(R.layout.fragment_notes, container, false);

        return f;
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);
        saveNotes = view.findViewById(R.id.saveNotes);

        saveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddNotesActivity.class);
                startActivity(intent);

            }
        });
    }
}