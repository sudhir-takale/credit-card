package com.amaap.creditcard.controller.dto;

import java.util.Objects;

public class Response {
    private HttpsStatus httpsStatus;
    private String response;

    public Response(HttpsStatus httpsStatus, String response) {
        this.httpsStatus = httpsStatus;
        this.response = response;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response1 = (Response) o;
        return httpsStatus == response1.httpsStatus && Objects.equals(response, response1.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpsStatus, response);
    }

    public HttpsStatus getHttpsStatus() {
        return httpsStatus;
    }

    public void setHttpsStatus(HttpsStatus httpsStatus) {
        this.httpsStatus = httpsStatus;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
