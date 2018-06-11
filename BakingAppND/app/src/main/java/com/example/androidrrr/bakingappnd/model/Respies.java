package com.example.androidrrr.bakingappnd.model;

public class Respies {
    private String respieId;
    private String respieName;
    public Respies(String id, String name)
    {
        respieId=id;
        respieName=name;
    }

    public String getRespieId() {
        return respieId;
    }

    public void setRespieId(String respieId) {
        this.respieId = respieId;
    }

    public String getRespieName() {
        return respieName;
    }

    public void setRespieName(String respieName) {
        this.respieName = respieName;
    }
}
