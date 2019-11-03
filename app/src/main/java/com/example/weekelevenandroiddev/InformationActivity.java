package com.example.weekelevenandroiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONObject info = response.getJSONObject();
                            JSONObject summary = (JSONObject)info.get("summary");
                            int numFriends = (int)summary.get("total_count");
                            TextView textView = findViewById(R.id.stuff);
                            textView.setText("You have " + numFriends + " friends.");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        request.executeAsync();
    }
}
