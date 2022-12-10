package com.example.signupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import org.w3c.dom.Document;

public class AddNotesActivity extends AppCompatActivity {
    EditText textNote, noteDetails;
    ImageButton saveNotes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        textNote = findViewById(R.id.textNote);
        noteDetails = findViewById(R.id.noteDetails);
        saveNotes = findViewById(R.id.saveNotes);

        saveNotes.setOnClickListener((v)-> saveNote());

    }

    void saveNote(){

        String title = textNote.getText().toString();
        String details = noteDetails.getText().toString();

        if (title==null | title.isEmpty()){
            textNote.setError("Note Title is Missing!!");
            return;
        }

        Notes notes = new Notes();
        notes.setTitle(title);
        notes.setDescription(details);
        saveNotesToFirebase(notes);
    }

    void saveNotesToFirebase(Notes notes){

        DocumentReference documentReference;
        documentReference = Utilities.getCollectionReferenceForNotes().document();

        documentReference.set(notes).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    // Notes has been added
                    Toast.makeText(AddNotesActivity.this, "Notes has been added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AddNotesActivity.this, "Failed while adding the notes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}