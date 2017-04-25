package buddybuild.com.ultron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squareup.moshi.Moshi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {
    // TODO: 2017-04-25 Inject this, probably?
    private final Buddybuild service;

    public MainActivity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.buddybuild.com/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        service = retrofit.create(Buddybuild.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Call<Build> buildCall = service.latestBuild("Bearer " + getString(R.string.buddybuild_token), "58ff5bab95effc0001705c93", "master");

        buildCall.enqueue(new Callback<Build>() {
            @Override
            public void onResponse(Call<Build> call, Response<Build> response) {
                System.out.println(call.request().url().toString());

                TextView number = (TextView) findViewById(R.id.build_number);
                number.setText(response.body().getId());

                TextView status = (TextView) findViewById(R.id.build_status);
                status.setText(response.body().getBuildStatus());
            }

            @Override
            public void onFailure(Call<Build> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void trigger(View button) {
        Call<Build> buildCall = service.trigger("Bearer " + getString(R.string.buddybuild_token), "58ff5bab95effc0001705c93", "master");
        buildCall.enqueue(new Callback<Build>() {
            @Override
            public void onResponse(Call<Build> call, Response<Build> response) {
                System.out.println(call.request().url().toString());
                System.out.println(response.raw().toString());

                TextView number = (TextView) findViewById(R.id.build_number);
                number.setText(response.body().getId());
            }

            @Override
            public void onFailure(Call<Build> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
