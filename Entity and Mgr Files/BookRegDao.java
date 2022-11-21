package bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Criterion;
import net.sf.hibernate.expression.Example;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import entity.BookReg;
import entity.AuthorReg;


public class BookRegDao
{

    private static transient Logger logger = LoggerFactory.getInstance().getLogger(BookRegDao.class.getName());

    public BookRegDao()
    {
        super();
    }

    public BookRegDao(Session session)
    {
        super(session);
    }


    public BookReg performRetrieveBookByISBN(String isbn)
        throws DaoException
    {
        BookReg result = null;

        try
        {
            Criteria crit = getSession().createCriteria(BookReg.class) //
                .add(Expression.eq("isbn", isbn)); 
            
            result = crit.uniqueResult();
    }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
        finally
        {
            return result;
        }
    }

    
    public BookReg performAddBook(BookReg entity)
        throws DaoException
    {
        logger.debug(LOG_START + entity);

        entity = saveOrUpdate(entity);

        logger.debug(LOG_END);
        return entity;
    }

    public BookReg performUpdateBook(BookReg entity)
        throws DaoException
    {
        return saveOrUpdate(entity);
    }

    public CsdeReg saveOrUpdate(BookReg entity)
        throws DaoException
    {
        logger.debug(LOG_START + entity);
        Bookreg retBookReg = null;
        try
        {
            retBookReg = (BookReg) getSession().saveOrUpdate(entity);
            getSession().flush();
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        logger.debug(LOG_END);
        return retBookReg;
    }

    // public CsdeReg update(BookReg entity)
    //     throws DaoException
    // {
    //     logger.debug(LOG_START + entity);
    //     Bookreg retBookReg = null;
    //     try
    //     {
    //         retBookReg = (BookReg) getSession().saveOrUpdate(entity);
    //         getSession().flush();
    //     }
    //     catch (HibernateException e)
    //     {
    //         logger.error(e.getMessage(), e);
    //         throw new DaoException(e.getMessage(), e);
    //     }
    //     logger.debug(LOG_END);
    //     return retBookReg;
    // }

    public BookReg performRetrieveBookByISBNandAuthor(String isbn, AuthorReg authorReg)
        throws DaoException
    {
        BookReg result = null;

    //idea is to retrieve out the author records from author table given N number of authors and reference it to the book from its isbn, if matches N number of authors and book isbn/title, return result

    //     try
    //     {
    //         Criteria crit = getSession().createCriteria(BookReg.class) //
    //             .add(Expression.eq("isbn", isbn)); 
            
    //         result = crit.uniqueResult();
    // }
    //     catch (HibernateException e)
    //     {
    //         e.printStackTrace();
    //         throw new DaoException(e.getMessage());
    //     }
        finally
        {
            return result;
        }
    }

    public BookReg performReturnBook(BookReg entity)
        throws DaoException
    {
        return saveOrUpdate(entity);
    }


    public void performDeleteBook(BookReg bookReg)
        throws DaoException
    {
        BookReg entity = (BookReg) bookReg;

        super.delete(entity);
        flush();

        logger.debug(LOG_END);
    }

    
}
