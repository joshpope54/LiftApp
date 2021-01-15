package dev.joshpope.lift.data.model;

import dev.joshpope.lift.data.Result;

public interface LoginCallback {

    void onSucess(Result<LoggedInUser> value);
    void onFailure();
}