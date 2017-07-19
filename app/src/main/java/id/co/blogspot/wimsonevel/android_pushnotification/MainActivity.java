package id.co.blogspot.wimsonevel.android_pushnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Switch switchNews = (Switch) findViewById(R.id.switchNews);
        Switch switchPromo = (Switch) findViewById(R.id.switchPromo);
        Button btnToken = (Button) findViewById(R.id.btn_token);

        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = FirebaseInstanceId.getInstance().getToken();
                TextView tvToken = (TextView) findViewById(R.id.tv_token);
                tvToken.setText(getResources().getString(R.string.token, token));

                Log.i("TOKEN", token);
            }
        });

        switchNews.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("news");
                    Toast.makeText(getApplicationContext(), "Subscribe to News Topic" , Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("news");
                    Toast.makeText(getApplicationContext(), "Unsubscribe from News Topic" , Toast.LENGTH_SHORT).show();
                }

            }
        });

        switchPromo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("promo");
                    Toast.makeText(getApplicationContext(), "Subscribe to Promo Topic", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("promo");
                    Toast.makeText(getApplicationContext(), "Unsubscribe from Promo Topic", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
