package com.example.triviaapp.data;

public class Question {
    public String ques;
    public Boolean ans;
    Question(String q,Boolean a)
    {
        ques=q;
        ans=a;
    }

    public void setAns(Boolean ans) {
        this.ans = ans;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public Boolean getAns() {
        return ans;
    }

    public String getQues() {
        return ques;
    }
}
