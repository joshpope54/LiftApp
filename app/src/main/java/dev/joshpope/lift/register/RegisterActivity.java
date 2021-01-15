package dev.joshpope.lift.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dev.joshpope.lift.LiftAPI;
import dev.joshpope.lift.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    static final String BASE_URL = "http://192.168.0.7:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText usernameEditText = findViewById(R.id.usernameRegister);
        final EditText emailEditText = findViewById(R.id.emailRegister);
        final EditText passwordEditText = findViewById(R.id.passwordRegister);
        final Button signupButton = findViewById(R.id.signup);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(logging);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();

                LiftAPI api = retrofit.create(LiftAPI.class);
                RegisterCredentials credentials = new RegisterCredentials(username, email, password);

                Call<ReturnedJson> call = api.register("application/json", credentials);
                call.enqueue(new Callback<ReturnedJson>() {
                    @Override
                    public void onResponse(Call<ReturnedJson> call, Response<ReturnedJson> response) {
                        ReturnedJson json = response.body();
                        System.out.println(json.getError());
                        System.out.println(json.getResult());
                        if(json.getError().equals("")){
                            Toast.makeText(RegisterActivity.this, json.getResult(), Toast.LENGTH_LONG).show();
                            RegisterActivity.this.finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, json.getError(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReturnedJson> call, Throwable t) {

                    }
                });
            }
        });
    }
}

//read from each box
//send with signup
//might aswell just finish()
