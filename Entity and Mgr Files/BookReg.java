package entity;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class BookReg
{

    public static final String ENTITY_NAME = "BookReg";


    
    private String isbn;

    
    private String title;

    
    private int year;

    
    private double price;

    
    private String genre;

    //private Author[] obj = new Author[2];

    private Set authorRegs;

    /** default constructor */
    public BookReg()
    {
        super();
        this.authorRegs = new LinkedHashSet();
    }

    public String getIsbn()
    {
        return this.isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
    //Here add on rest of getter setters

    //end getter setterse

    public Set getAuthorRegs()
    {
        return this.authorRegs;
    }
    
    public void setAuthorRegs(Set authorRegs)
    {
        this.authorRegs = authorRegs;
    }


    public AuthorReg getAuthorReg(String authorName, String dob)
    {
        if (authorName != null && dob != null)
        {
            for (Iterator it = authorRegs.iterator(); it.hasNext();)
            {
                AuthorReg authorReg = (AuthorReg) it.next();
                if (authorReg.getauthorName.equals(authorName) && authorReg.getdob.equals(dob))
                    return authorReg;
            }
        }
        return null;
    }

    public void addAuthorReg(AuthorReg authorReg)
    {
        if (authorReg != null)
            if (!authorRegs.contains(authorReg))
            {
                authorReg.setauthorName(authorReg.getAuthorName);
                authorReg.setdob(this);
                this.authorRegs.add(authorReg);
            }
    }


}