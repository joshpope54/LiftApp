package dev.joshpope.lift.register;

public class ReturnedJson {

    private String error;
    private String result;

    public ReturnedJson(String error, String result) {
        this.error = error;
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public String getResult() {
        return result;
    }
}