package date_base;

public class Main  {

	public static void main(String[] args) throws Exception {
		create_quiz cq=new create_quiz();
		Durat d=new Durat();
		d.SetH(01);
		d.SetMin(01);
		d.SetSec(01);
		cq.createQuiz(100,"laravel",5,20,d);
	//DataAccess dd=new DataAccess();
	//dd.deleteQuiz("lal",777);

	}


}
