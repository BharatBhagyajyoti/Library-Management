package bean_pojo;

public class BookBean {
	private String BookId;
	private String BookName;
	private String Author;
	private double Price;
	private int Quantity;
	private String Availability;
	
	public String getAvailability()
	{
		return Availability;
	}
	public void setAvailability(String Availability)
	{
		this.Availability=Availability;
	}
	
	
	public String getBookId() {
		return BookId;
	}
	public void setBookId(String bookId) {
		BookId = bookId;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	

}
