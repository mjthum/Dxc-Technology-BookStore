package bookstore;

import java.io.Serializable;
import java.util.List;

import entity.BookReg;
import entity.AuthorReg;

import entity.UserInfo;


public interface BookRegMgr
{

	
	public BookReg performRetrieveBookByISBN(String isbn);	

	public BookReg performAddBook(BookReg bookReg);
	
	public BookReg performUpdateBook(BookReg bookReg);

	public BookReg performRetrieveBookByISBNandAuthor(String isbn, AuthorReg authorReg);	

	public BookReg performReturnBook(BookReg bookReg);

	public void performDeleteBook(BookReg bookReg);
}
