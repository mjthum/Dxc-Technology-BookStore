package bookstore.delegate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import bookstore.BookRegMgr;
import entity.BookReg;
import entity.AuthorReg;


public class BookRegMgrDelegate{
;
	public static final String DELEGATE_NAME = BookRegMgrDelegate.class
			.getName();

	private static final Log logger = LogFactory
			.getLog(DeathRegMgrDelegate.class);


	public BookReg performRetrieveBookByISBN(String isbn){

		BookReg entity = (BookReg) BookMgrDelegate.getInstance().execute(isbn {

					public Serializable execute(Serializable value) {
						return bookRegMgr().performRetrieveBookByISBN((BookReg) value);
					}
				});

		logger.debug(method + LogConstants.LOG_END + entity);
		return entity;
	}

	public BookReg performAddBook(BookReg bookReg){

		BookReg entity = (BookReg) BookMgrDelegate.getInstance().execute(bookReg {

					public Serializable execute(Serializable value) {
						return bookRegMgr().performAddBook((BookReg) value);
					}
				});

		logger.debug(method + LogConstants.LOG_END + entity);
		return entity;
	}

	public BookReg performUpdateBook(BookReg bookReg){

		BookReg entity = (BookReg) BookMgrDelegate.getInstance().execute(bookReg {

					public Serializable execute(Serializable value) {
						return bookRegMgr().performUpdateBook((BookReg) value);
					}
				});

		logger.debug(method + LogConstants.LOG_END + entity);
		return entity;
	}

	public BookReg performRetrieveBookByISBNandAuthor(String isbn, AuthorReg authorReg){

		BookReg entity = (BookReg) BookMgrDelegate.getInstance().execute(isbn,authorReg {

					public Serializable execute(Serializable value) {
						return bookRegMgr().performRetrieveBookByISBNandAuthor((BookReg) value);
					}
				});

		logger.debug(method + LogConstants.LOG_END + entity);
		return entity;
	}

	public BookReg performReturnBook(BookReg bookReg){

		BookReg entity = (BookReg) BookMgrDelegate.getInstance().execute(bookReg {

					public Serializable execute(Serializable value) {
						return bookRegMgr().performUpdateBook((BookReg) value);
					}
				});

		logger.debug(method + LogConstants.LOG_END + entity);
		return entity;
	}

	public void performDeleteBook(BookReg bookReg){

		BookReg entity = (BookReg) BookMgrDelegate.getInstance().execute(bookReg {

					public Serializable execute(Serializable value) {
						bookRegMgr().performDeleteBook((BookReg) value);
						return null;
					}
				});

		logger.debug(method + LogConstants.LOG_END + entity);
	}


	
}
