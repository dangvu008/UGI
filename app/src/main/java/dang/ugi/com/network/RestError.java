package dang.ugi.com.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DANG on 12/31/2016.
 */

public class RestError {

    private boolean status;
    private String error;

    public RestError() {
    }

    public RestError(String error) {
        this.status = false;
        this.error = error;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("error", error);
            jsonObject.put("status", status);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
