package com.istiaq66.momentous_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.istiaq66.momentous_click.Fragments.Booked_frag;
import com.istiaq66.momentous_click.Fragments.Client_frag;
import com.istiaq66.momentous_click.Fragments.Completed_frag;
import com.istiaq66.momentous_click.Fragments.Home_frag;
import com.istiaq66.momentous_click.Fragments.Incomplete_frag;

public class MainActivity extends AppCompatActivity {



    private static final String TAG = "PushNotification";
    private static final String CHANNEL_ID = "101";
    private  long backPressedTime;

    BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---------Hide Actionbar----------//
        getSupportActionBar().hide();


        createNotificationChannel();
        subscribe();


        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new Home_frag()).commit();
        navigationView.setSelectedItemId(R.id.Home);




        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

                    case R.id.Home:
                        fragment = new Home_frag();
                        break;

                    case R.id.Booked:
                        fragment = new Booked_frag();
                        break;

                    case R.id.Incomplete:
                        fragment = new Incomplete_frag();
                        break;

                    case R.id.Completed:
                        fragment = new Completed_frag();
                        break;

                    case R.id.Clients:
                        fragment = new Client_frag();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

                return true;
            }
        });


    }



    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "firebaseNotifChannel";
            String description = "Receive Firebase notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void subscribe(){
        FirebaseMessaging.getInstance().subscribeToTopic("Momentous")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

    }



    //----Exit app----//
    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000>System.currentTimeMillis())
        {
            super.onBackPressed();
            this.finishAffinity();
            return;
        }
        else
        {
            Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }
}