package dev.joshpope.lift;

import dev.joshpope.lift.data.model.Credentials;
import dev.joshpope.lift.data.model.LoggedInUser;
import dev.joshpope.lift.register.RegisterCredentials;
import dev.joshpope.lift.register.ReturnedJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LiftAPI {
    @POST("login")
    Call<LoggedInUser> login(@Header("Content-Type") String content_type, @Body Credentials user);

    @POST("register")
    Call<ReturnedJson> register(@Header("Content-Type") String content_type, @Body RegisterCredentials user);
}
