package com.example.luis.iotproject;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    Button on;
    Button off;
    TextView Ultra;
    TextView message;
    TextView percentage;
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    int n;
    int Level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = NotificationManagerCompat.from(this);

/**
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#da4747"));

            Bitmap bg = Bitmap.createBitmap(480,280, Bitmap.Config.ARGB_8888); 
            Canvas canvas = new Canvas(bg);
            canvas.drawRect(50,50,200,100,paint);

            LinearLayout ll = (LinearLayout) findViewById(R.id.rect);
            ll.setBackground(new BitmapDrawable(bg));
**/



      //  on = (Button) findViewById(R.id.button_on);
    //    off = (Button) findViewById(R.id.button_off);
       // ultra = (Button) findViewById(R.id.button_Ultra);
/*
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STATUS");
                myRef.setValue(1);
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STATUS");
                myRef.setValue(0);
            }
        });


        */
     //   Ultra = (TextView) findViewById(R.id.textView_Ultra);
        message = (TextView) findViewById(R.id.textView_message);
        percentage = (TextView) findViewById(R.id.textView_percentage) ;

     //  ultra.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
   //     DatabaseReference myRef = database.getReference("ULTRASONIC_VALUE");
        DatabaseReference myRef1 = database.getReference("Message");
        DatabaseReference myRef2 = database.getReference("Percentage");

/*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated
                Integer value = dataSnapshot.getValue(Integer.class);
               // Integer value = Integer.valueOf(dataSnapshot.getValue().toString());

               // Integer value = Integer.valueOf(dataSnapshot.getValue(Integer.class));
                //Integer value =Integer.valueOf(dataSnapShot.getValue());
               // int value = (int) dataSnapshot.getValue();
                Ultra.setText( "" + value + " cm");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
*/
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value1 = dataSnapshot.getValue(String.class);
               // Integer value = dataSnapshot.getValue(Integer.class);
                //Integer value =Integer.valueOf(dataSnapShot.getValue());
                message.setText( "" + value1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value2 = dataSnapshot.getValue(Integer.class);
                percentage.setText( "" + value2 + "%");
                Level = value2;


                final ProgressBar progressBar = (ProgressBar) findViewById(R.id.vertical_bar);
                progressBar.setProgress((Level));
                if(value2 > 80)
                    notifyme();




            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        }
        );

    }

    public void notifyme(){
        String title = "Trash is full!";

        String message =  "Time to collect";

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setVibrate(new long[] { 1000, 1000})
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);

    }

}
