package date_base;

public class Question extends DataAccess{
    protected String New_Question;
    protected String Chosen;
    protected String array[]= new String[3];
    protected int ScorePoint ;
    protected String SkillType;
    protected String CorrectAnswer;
    protected String Title;
    public void SetQuestion(String quest)
    {
        this.New_Question=quest;
    }
    public String GetQuestion()
    {
        return New_Question;
    }
    public void SetAnswers(String Answer)
    {
        this.Chosen=Answer;
    }
    public String GetAnswers()
    {
        return Chosen;
    }
    public void SetSkillType(String Type)
    {
        this.SkillType=Type;
    }
    public String GetSkillType()
    {
        return SkillType;
    }
    public void SetCorrectAnswer (String ans)
    {
        this.CorrectAnswer=ans;
    }
    public String GetCorrectAnswer()
    {
        return CorrectAnswer;
    }
    public void SetScorePoint (int score)
    {
        this.ScorePoint=score;
    }
    public int GetScorePoint()
    {
        return ScorePoint;
    }



}
