package midterm;

import java.io.Serializable;

public class Quotation implements Serializable 
{
    private String quote;
    private String author;
    private int publicId;
    private int count;
    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private static int idFlag = 0;

    private static int generatePublicId()
    {
        return idFlag++;
    }

    public Quotation() {
        this.setPublicId(generatePublicId());
    }

    public Quotation(String quote, String author) {
        this.quote = quote;
        this.author = author;
        this.setPublicId(generatePublicId());
    }

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublicId() {
		return publicId;
	}

	public void setPublicId(int publicId) {
		this.publicId = publicId;
	}




}
