package date_base;

import java.time.Duration;

public class Quiz  {
    public String Title;
    public String SkillType;
    protected int PassScore;
    public int NumberOfQuestions;
    public Duration duration;
    public Question[] Questions =new Question[NumberOfQuestions];
    public int FinalScore ;
    Durat objct = new Durat();
}
