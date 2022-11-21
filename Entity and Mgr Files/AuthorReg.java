package entity;

import java.util.Date;


public class AuthorReg

{

    public static final String ENTITY_NAME = "AuthorReg";


    /** nullable persistent field */
    private String authorName;

    /** nullable persistent field */
    private String authorDob;

    /** default constructor */
    public AuthorReg()
    {
        super();
    }


    public String getAuthorName()
    {
        return this.authorName;
    }

    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    public String getAuthorDob()
    {
        return this.authorDob;
    }

    public void setAuthorDob(String authorDob)
    {
        this.authorDob = authorDob;
    }


}
