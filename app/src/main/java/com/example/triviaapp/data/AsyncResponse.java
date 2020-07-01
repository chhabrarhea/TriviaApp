package com.example.triviaapp.data;

import java.util.ArrayList;

public interface AsyncResponse {
    void processFinished(ArrayList<Question> questionsList);
}
