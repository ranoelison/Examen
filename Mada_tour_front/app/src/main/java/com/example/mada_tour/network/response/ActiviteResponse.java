package com.example.mada_tour.network.response;

import com.example.mada_tour.modele.Activite;
import com.google.gson.annotations.SerializedName;

public class ActiviteResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Activite[] data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Activite[] getData() {
        return data;
    }

    public void setData(Activite[] data) {
        this.data = data;
    }
}
