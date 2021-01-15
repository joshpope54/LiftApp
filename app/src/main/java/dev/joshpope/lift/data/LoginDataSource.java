package dev.joshpope.lift.data;

import android.util.Log;

import java.io.IOException;

import dev.joshpope.lift.LiftAPI;
import dev.joshpope.lift.data.model.Credentials;
import dev.joshpope.lift.data.model.LoggedInUser;
import dev.joshpope.lift.data.model.LoginCallback;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    static final String BASE_URL = "http://192.168.0.7:8000/";

    public void login(String email, String password, LoginCallback loginCallback) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        // TODO: handle loggedInUser authentication

        LiftAPI api = retrofit.create(LiftAPI.class);
        Credentials credentials = new Credentials(email, password);

        //get inforrmation -^ credentials object
        //create thread
        //request data from api using retro fit
        //return data

        Call<LoggedInUser> call = api.login("application/json", credentials);
        call.enqueue(new Callback<LoggedInUser>() {
            @Override
            public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {
                if(response.isSuccessful()) {
                    LoggedInUser loggedInUser = response.body();
                    if(loggedInUser.getToken()==null){
                        loginCallback.onSucess(new Result.Error(new IOException("Invalid Something")));
                    }else{
                        loginCallback.onSucess(new Result.Success<>(loggedInUser));
                    }


                }
            }

            @Override
            public void onFailure(Call<LoggedInUser> call, Throwable t) {
                Log.e("ERR: LIFT", "Unable to submit post to API.");
                loginCallback.onFailure();
            }
        });
    }

    public void logout() {
        // TODO: revoke authentication
    }
}