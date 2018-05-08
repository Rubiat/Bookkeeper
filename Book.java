/** -------------------------------------------------------
 * Assignment 1
 * Written by: Rubiat Zaman, 40062082
 * Submitted on Github
 * For COMP 249 Section PA – Winter 2018
 * Due date: January 31st 2018
 * This program has a 'Book' class that contains 4 attributes. The goal is to create a
 * bookkeeper software that can keep track and modify the attributes of the books 
 *  -------------------------------------------------------- */

import java.util.Scanner;
public class Book {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);

		int maxBooks;
		int option;  /** int inputted by the user to choose option from main menu */
		final String password = "password";
		String passEntered1, passEntered2;  /** the passwords inputted to enter option 1 and 2 */
		int attempt1, repeatAttempts = 0, attempt2;  /** attempt1 used for option 1, attempt2 used for option2, repeatAttempts used during option 1 */

		System.out.println("Welcome to Rubiat's Bookkeeper Software");

		System.out.println("\nPlease enter the maximum number of books the bookstore can contain");
		maxBooks = input.nextInt();
		Book [] inventory = new Book [maxBooks];


		do {  /** do-while loop so that it always comes back to the main menu after each option */

			do {
				Book.mainMenu();  /** method created to display the options */

				option = input.nextInt();
			}
			while (option < 1 || option > 5);

			switch (option) /** different action for different options/cases */
			{

			case 1:

				attempt1 = 0;  /**  number of attempts to input the correct password by the user */

				do {
					System.out.println("Please enter your password");
					passEntered1 = input.next();
					attempt1++;
				}
				while (attempt1 < 3 && (passEntered1.equals(password) == false));  /** prompt user for password unless entered incorrectly 3 times */

				if (attempt1 == 3) {  /** if entered incorrectly 3 times... */
					repeatAttempts++;  
					if (repeatAttempts == 4) {
						System.out.println("Program has detected suspicious activities"
								+ " and will terminate immediately!");
						System.exit(0);
					}
				}

				if (passEntered1.equals(password)) {  /**  when user enters the password correctly */
					System.out.println("How many books do you wish to enter");
					int newBooks;  /** number of books user wishes to add */
					do {
						newBooks = input.nextInt();

						if (newBooks > (maxBooks - findNumberOfCreatedBooks()) && newBooks != 0)  /** if (newBooks is bigger than available slots) */
							System.out.println("You can only add " + (maxBooks - findNumberOfCreatedBooks()) + " book(s)");
					} while (newBooks > (maxBooks - findNumberOfCreatedBooks()));  /** reprompt the user until they enter valid number */

					for (int i = 1; i<= newBooks; i++) {  /** user inputs the info of the desired new book here */
						input.nextLine();
						System.out.println("\nTitle of book " + i + ":");
						String title = input.nextLine();
						System.out.println("\nAuthor of book " + i + ":");
						String author = input.nextLine();
						System.out.println("ISBN of book " + i + ":");
						long ISBN = input.nextLong();
						System.out.println("Price of book " + i + ":");
						double price = input.nextDouble();
						inventory [findNumberOfCreatedBooks()] = new Book (title, author, ISBN, price);
						System.out.println("New book created");
					}
				}
				break;   /** breaks but goes back to main menu because of do-while loop */

			case 2: 

				attempt2 = 0;  /** same as case 1 */

				do {
					System.out.println("Please enter your password");
					passEntered2 = input.next();
					attempt2++;
				} while (attempt2 < 3 && (passEntered2.equals(password) == false));

				if (passEntered2.equals(password)) { /** when the user enters the pass correctly */
					System.out.println("Which book (index number) do you wish to update?");
					int index = input.nextInt();

					if (inventory[index] == null) {  /** if the slot entered by the user does not contain any book */
						do {
							System.out.println("Index location is empty. Enter another "
									+ "number or -1 to go to main menu");
							index = input.nextInt();
							if (index == -1)
								break; /** user enters this if they wish to go back to main menu */
						} while (inventory[index] == null);  /** keep prompting until user enters valid slot or decides to go to main menu */
					}
					
					if (index == -1)
						break; /** break again so that it breaks out of the outer while loop */
					
					if (inventory[index] != null) {  /**  if this index has a book */
						System.out.println("Book # " + index + 
								"\nAuthor: " + inventory[index].getAuthor() + 
								"\nTitle: " + inventory[index].getTitle() + 
								"\nISBN: " + inventory[index].getISBN() +
								"\nPrice: $" + inventory[index].getPrice());
						
						int choice; /** choices for option 2 menu */
						do {
						System.out.println("\nWhich information would you like to change? "
								+ "\n\t1. author"
								+ "\n\t2. title"
								+ "\n\t3. ISBN"
								+ "\n\t4. price"
								+ "\n\t5. Quit"
								+ "\nEnter your choice");
						
						choice = input.nextInt();
						switch (choice) /** different cases/options for the options in option 2 */
						{
						case 1: 
							System.out.println("Enter new author:");
							String newAuthor = input.next();
							inventory[index].setAuthor(newAuthor);
							System.out.println(inventory[index]); /** prints the info of the book to display the change */
							break;
							
						case 2: 
							System.out.println("Enter new title");
							String newTitle = input.next();
							inventory[index].setTitle(newTitle);
							System.out.println(inventory[index]);
							break;
							
						case 3: 
							System.out.println("Enter new ISBN");
							long newIsbn = input.nextLong();
							inventory[index].setISBN(newIsbn);
							System.out.println(inventory[index]);
							break;
							
						case 4:
							System.out.println("Enter new price");
							double newPrice = input.nextDouble();
							inventory[index].setPrice(newPrice);
							System.out.println(inventory[index]);
							break;
							
						case 5:
							break;
							
						default: ;
						} /** end of switch for option 2 options */
						} while (choice != 5); /** if user chooses 5 then it quits the option 2 menu */
					}
				}
				break;
				
			case 3: 
				
				System.out.println("Author: ");
				String specificAuthor = input.next();
				for (int i = 0; i <NumOfBooks; i++) {
					if (inventory[i].getAuthor().equals(specificAuthor))
						System.out.println(inventory[i]);  /** if the book has same author as user input, it prints its info */
				}
				
				break;
				
			case 4:
				
				System.out.println("Price: ");
				double specificPrice = input.nextDouble();
				for (int i = 0; i < NumOfBooks; i++) {
					if (inventory[i].getPrice() < specificPrice)
						System.out.println(inventory[i]);   /** book prints its info if it has the same price as the user input */
				}
				
				break;
				
			case 5: 
				
				System.out.println("Thank you for using the Bookkeeper Software");
				System.exit(0);

			}
		} while (true);
		
		
	}






	private String title, author;
	private long ISBN;
	private double price;
	private static int NumOfBooks = 0;

	/** default constructor */
	public Book () { 
		title = "Title of book";
		author = "Author";
		ISBN = 00000000;
		price = 0;
		NumOfBooks++;
	}

	/** constructor used to create new books for the option 1 */
	public Book (String title, String author, long ISBN, double price) {  
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.price = price;
		NumOfBooks++;
	}
	
	public Book(Book b) {
		this.title = b.title;
		this.author = b.author;
		this.ISBN = b.ISBN;
		this.price = b.price;
		NumOfBooks++;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public void setAuthor (String author) {
		this.author = author;
	}

	public void setISBN (long ISBN) {
		this.ISBN = ISBN;
	}

	public void setPrice (double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public long getISBN () {
		return ISBN;
	}

	public double getPrice () {
		return price;
	}

	public String toString () {
		return ("Title: " + title + "\nAuthor : " + author + "\nISBN: " + ISBN + "\nPrice: $" + price + "\n");
	}

	/** compares isbn and price */
	public boolean equals (Book b) {
		return (ISBN == b.ISBN && price == b.price);
	}

	/** returns total number of books created so far */
	public static int findNumberOfCreatedBooks() {
		return NumOfBooks;
	}

	
	/** displays the main menu */
	public static void mainMenu() {
		System.out.println("What do you want to do?"
				+ "\n\t1.	Enter new books (password required)" 
				+ "\n\t2.	Change information of a book (password required)" 
				+ "\n\t3.	Display all books by a specific author"
				+ "\n\t4.	Display all books under a certain price" 
				+ "\n\t5.	Quit"
				+ "\nPlease enter your choice");
	}

}
