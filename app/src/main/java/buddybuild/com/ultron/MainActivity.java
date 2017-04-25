package buddybuild.com.ultron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.buddybuild.com/v1/")
                .build();

        Buddybuild service = retrofit.create(Buddybuild.class);
        service.latestBuild("", "", "master")
    }

}
