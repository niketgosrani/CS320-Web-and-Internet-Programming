package onlineQuiz;

import java.io.Serializable;

public class Test implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String question;
	private String answer;
	private int questId;

	public Test(int id, String question,String answer) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
	}
	
	  public Test(String question, String answer) {
	        this.question = question;
	        this.answer = answer;
	        
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getquestId() {
		return questId;
	}

	public void setquestId(int questId) {
		this.questId = questId;
	}

}
