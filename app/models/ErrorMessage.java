package models;

public class ErrorMessage {
    public int status;
    public String error;

    public ErrorMessage(int status, String error){
        this.status = status;
        this.error = error;
    }
}
