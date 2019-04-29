package date_base;

public class Question {
    protected String chosen;
    protected String quest;
    protected String CorrectAnswer;
    public void SetCorrectAnswer (String ans)
    {

        CorrectAnswer=ans;
    }
    public String GetCorrectAnswer()
    {

        return CorrectAnswer;
    }

    public void setQuestion (String newQuestion)
    {
        quest=newQuestion;
    }
    public String getQuestion()
    {
        return quest;
    }



}
