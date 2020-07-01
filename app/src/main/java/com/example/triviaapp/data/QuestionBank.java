package com.example.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.appController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionBank {
    public ArrayList<Question> questions;
    private String url="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
   public QuestionBank()
    {
        questions=new ArrayList<>();
    }

    public ArrayList<Question> getQuestions(final AsyncResponse callBack) {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,url,(JSONArray) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
              for(int i=0;i<response.length();i++)
              {
                  try {

                      questions.add(new Question(response.getJSONArray(i).get(0).toString(),response.getJSONArray(i).getBoolean(1)));
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }

                if( callBack!=null)
                {
                    callBack.processFinished(questions);
                }}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Log.i("error",""+error);
            }
        });
        appController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questions;
    }
}
