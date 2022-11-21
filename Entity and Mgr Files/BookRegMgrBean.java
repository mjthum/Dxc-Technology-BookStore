package bookstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;


import entity.BookReg;
import entity.AuthorReg;



public class BookRegMgrBean
    implements BookRegMgr
{

     public BookReg performRetrieveBookByISBN(String isbn)
        
    {
        String METHOD_NAME = "[performRetrieveBookByISBN] ";

        BookRegDao bookRegDao = null;
        try
        {

            bookReg = bookRegDao.performRetrieveBookByISBN(isbn);
        }
        catch (Exception ex)
        {
            logger.fatal(METHOD_NAME + "DaoException", ex);
        }
        finally
        {
            try
            {
                if (bookReg != null)
                {
                    bookRegDao.close();
                }
            }
            catch (DaoException e)
            {
                logger.fatal(METHOD_NAME + "DaoException", e);
            }
        }

        logger.fatal(METHOD_NAME + "End...");

        return bookReg;
    }

    public BookReg performAddBook(BookReg bookReg)
        
    {
        String METHOD_NAME = "[performAddBook] ";

        BookRegDao bookRegDao = null;
        try
        {

            bookReg = bookRegDao.performAddBook(bookReg);
        }
        catch (Exception ex)
        {
            logger.fatal(METHOD_NAME + "DaoException", ex);
        }
        finally
        {
            try
            {
                if (bookReg != null)
                {
                    bookRegDao.close();
                }
            }
            catch (DaoException e)
            {
                logger.fatal(METHOD_NAME + "DaoException", e);
            }
        }

        logger.fatal(METHOD_NAME + "End...");

        return bookReg;
    }

    public BookReg performUpdateBook(BookReg bookReg)
        
    {
        String METHOD_NAME = "[performUpdateBook] ";

        BookRegDao bookRegDao = null;
        try
        {

            bookReg = bookRegDao.performUpdateBook(bookReg);
        }
        catch (Exception ex)
        {
            logger.fatal(METHOD_NAME + "DaoException", ex);
        }
        finally
        {
            try
            {
                if (bookReg != null)
                {
                    bookRegDao.close();
                }
            }
            catch (DaoException e)
            {
                logger.fatal(METHOD_NAME + "DaoException", e);
            }
        }

        logger.fatal(METHOD_NAME + "End...");

        return bookReg;
    }

    public BookReg performRetrieveBookByISBNandAuthor(String isbn, AuthorReg authorReg)
        
    {
        String METHOD_NAME = "[performRetrieveBookByISBNandAuthor] ";

        BookRegDao bookRegDao = null;
        try
        {

            bookReg = bookRegDao.performRetrieveBookByISBNandAuthor(isbn, authorReg);
        }
        catch (Exception ex)
        {
            logger.fatal(METHOD_NAME + "DaoException", ex);
        }
        finally
        {
            try
            {
                if (bookReg != null)
                {
                    bookRegDao.close();
                }
            }
            catch (DaoException e)
            {
                logger.fatal(METHOD_NAME + "DaoException", e);
            }
        }

        logger.fatal(METHOD_NAME + "End...");

        return bookReg;
    }

    public BookReg performReturnBook(BookReg bookReg)
        
    {
        String METHOD_NAME = "[performReturnBook] ";

        BookRegDao bookRegDao = null;
        try
        {

            bookReg = bookRegDao.performRetrieveBookByISBNandAuthor(bookReg);
            //What I can propose is to pump in the number into the method to update the total number of copies in db
            //bookReg = bookRegDao.performRetrieveBookByISBNandAuthor(bookReg, 1);
        }
        catch (Exception ex)
        {
            logger.fatal(METHOD_NAME + "DaoException", ex);
        }
        finally
        {
            try
            {
                if (bookReg != null)
                {
                    bookRegDao.close();
                }
            }
            catch (DaoException e)
            {
                logger.fatal(METHOD_NAME + "DaoException", e);
            }
        }

        logger.fatal(METHOD_NAME + "End...");

        return bookReg;
    }

    public void performDeleteBook(BookReg bookReg)
        
    {
        String METHOD_NAME = "[performDeleteBook] ";

        BookRegDao bookRegDao = null;
        try
        {

            bookReg = bookRegDao.performDeleteBook(bookReg);

        }
        catch (Exception ex)
        {
            logger.fatal(METHOD_NAME + "DaoException", ex);
        }
        finally
        {
            try
            {
                if (bookReg != null)
                {
                    bookRegDao.close();
                }
            }
            catch (DaoException e)
            {
                logger.fatal(METHOD_NAME + "DaoException", e);
            }
        }

        logger.fatal(METHOD_NAME + "End...");

    }



}