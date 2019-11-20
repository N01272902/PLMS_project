package com.ceng319.plmsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class writeDB extends AppCompatActivity {

    private Button submit;
    private EditText name;
    private EditText availability;
    private EditText message;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_db);

        findAllViews();
        getDatabase();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(name.getText(), availability.getText(), message.getText());
            }
        });
    }

    private void writeData(Editable name, Editable availability, Editable message) {

        DataStructure mData = createData(name, availability, message);

        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Value was set. ", Toast.LENGTH_LONG).show();
               // gotoRead();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Writing Failed! ", Toast.LENGTH_LONG).show();
            }
        });
    }

    /*
    private void gotoRead() {
    //    Intent intent = new Intent(getApplicationContext(), readDB.class);
        startActivity(intent);
   }
   */

private DataStructure createData(Editable name, Editable availability, Editable message) {
        Long time = System.currentTimeMillis()/1000;
        String timestamp = time.toString();
        return new DataStructure(String.valueOf(name),
                String.valueOf(availability),
                String.valueOf(message),
                timestamp);
    }

    private void getDatabase() {
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String path = "userdata/" + mAuth.getUid();
        myRef = database.getReference(path);
    }

    private void findAllViews() {
        submit = findViewById(R.id.submit);
        name = findViewById(R.id.nameText);
        availability = findViewById(R.id.availabilityText);
        message = findViewById(R.id.messageText);
    }
}
