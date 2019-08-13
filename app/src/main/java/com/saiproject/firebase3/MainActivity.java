package com.saiproject.firebase3;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 *
 * Get data from children of the main node and put it in text form
 *
 *
 */

public class MainActivity extends AppCompatActivity {


    EditText edtBoxerName, edtPunchPower, edtPunchSpeed;

    Button btnSendData;
    TextView txtReceivedData;


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private String oldTextReceivedData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtBoxerName = findViewById(R.id.edtBoxerName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);


        btnSendData = findViewById(R.id.btnSendData);
        txtReceivedData  = findViewById(R.id.txtReceivedData);



        myRef = db.getReference();

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer boxerPower=0;
                String  boxerName = edtBoxerName.getText().toString();
                Integer boxerSpeed=0;

                try {
                    String string = edtPunchPower.getText().toString();
                    boxerPower =Integer.parseInt(string);

                }

                catch (Exception e){
                    e.printStackTrace();
                }


                try {
                    String string = edtPunchSpeed.getText().toString();
                    boxerSpeed =Integer.parseInt(string);

                }

                catch (Exception e){
                    e.printStackTrace();
                }

                Boxer boxer = new Boxer(boxerName,boxerPower,boxerSpeed);
                myRef.child(myRef.push().getKey()).setValue(boxer);         // New key is created per push and uploaded and the boxer added under


                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot boxerObject : dataSnapshot.getChildren()){

                            Boxer boxer = boxerObject.getValue(Boxer.class); //Give the type of data

                            if(oldTextReceivedData == null){
                                oldTextReceivedData="";
                            }

                            txtReceivedData.setText(oldTextReceivedData +
                                    boxer.getBoxerName() + "-" +
                                    boxer.getPunchPower() + "-" +
                                    boxer.getPunchSpeed()+"\n");

                            oldTextReceivedData = txtReceivedData.getText().toString();


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });



    }
}
