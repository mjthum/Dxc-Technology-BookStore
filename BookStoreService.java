package bookStore.webserviceRestful;

import java.io.FileOutputStream;
import org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mp.common.TransactionContext;
import com.mp.entity.Code;
import com.mp.log.Logger;
import com.mp.log.LoggerFactory;
import com.mp.util.validator.Validator;
import com.mp.util.validator.ValidatorFactory;
import com.mp.util.validator.exception.ValidatorException;
import entity.UserInfo;
import entity.AuthorReg;
import entity.BookReg;


@Path("/bookStore")
public class BookStoreService
{
    private static transient String CLASS_NAME = BookStoreService.class.getName();

    private static transient Logger logger = LoggerFactory.getInstance().getLogger(CLASS_NAME);

    static final String USERNAME = "";

    public BookStoreService()
    {
    }


    // To perform Add/Update/Return/Delete of Book Records
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/performAddUpdateReturnDeleteBookRecord")
    public Response performAddUpdateReturnDeleteBookRecord(String requestJson)
    {
        String METHOD_NAME = "performAddUpdateReturnDeleteBookRecord";
        logger.fatal(METHOD_NAME + "requestJson = " + requestJson);

        Response response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseRoot = objectMapper.createObjectNode().objectNode();
        //Assume existing class to fetch  User login information with role and authorization level
        UserInfo userInfo = getUserInfo();
        //assume existing class file for Author
        //AuthorReg.java will consist author name and dob
        AuthorReg authorReg = new authorReg();
        //assume existing class file for Book
        //bookReg.java  will consist of all the book fields + Author details + no. of copies of book
        BookReg bookReg = new bookReg();
        

        JsonNode root;
        try
        {
            root = objectMapper.readTree(requestJson);

            // to retrieve Book details
            JsonNode bookNode = root.path("BOOK");
            String isbn = bookNode.get("ISBN").asText();
            String bookTitle = bookNode.get("TITLE").asText();
            String bookAuthor = bookNode.get("AUTHOR").asText();
            String bookYear = bookNode.get("YEAR").asText();
            String bookPrice = bookNode.get("PRICE").asText();
            String bookGenre = bookNode.get("GENRE").asText();
            
            // to retrieve Author details
            JsonNode authorNode = root.get("AUTHOR");
            AuthorDto authorDto = null;
            
            //to cater for multiple authors
            if (authorNode.isArray())
            {
                for (JsonNode objNode : authorNode)
                {
                	authorDto = new authorDto();
                    String authorName = authorNode.get("NAME").asText();
                    String authorBirthday = authorNode.get("DOB").asText();
                    
                    authorReg.setAuthorName(authorName);
                    authorReg.setAuthorDob(authorBirthday);

                    bookReg.addAuthorReg(authorReg);

                }
            }
            bookReg.setIsbn(isbn);
            bookReg.setTitle(bookTitle);
            bookReg.setAuthor(bookAuthor);
            bookReg.setYear(bookYear);
            bookReg.setPrice(bookPrice);
            bookReg.setGenre(bookGenre);
            
            //assume there is transaction code sent with payload to determine which of the 4 actions is going to be performed 
            JsonNode txnNode = root.path("TXN");
            String transactionCode = txnNode.get("TRANSACTION_CODE").asText();
            

            switch(transactionCode) {
            	case "ADD":
            		//Check if current book entered is existing in current db
            		bookReg = performRetrieveBookByISBN(isbn);
            		if(null == bookReg) {
            			try {
            				//perform adding of book into DB using data from json payload
            				bookReg = performAddBook(bookReg);
            				//bookReg will contain added book details

            			}
            			catch (Exception e){
            				//print error if encountered
            				e.printStackTrace();
            				//Assume Error code E006 Represents System Error
            				responseRoot.put("STATUS", "E006");
            			}
            			//return success code E000 with book details
                        responseRoot.put("STATUS", "E000");
                        LinkedHashMap<String, String> responseMap = new LinkedHashMap<String, String>();
                        responseMap.put("ISBN", bookReg.getIsbn());
                        responseMap.put("TITLE", bookReg.getTitle());
                        responseMap.put("AUTHORS", bookReg.getAuthorReg());
                        responseMap.put("YEAR", bookReg.getYear());
                        responseMap.put("PRICE", bookReg.getPrice());
                        responseMap.put("GENRE", bookReg.getGenre());
                        
                        JsonNode responseNode = null;
                        responseNode = objectMapper.valueToTree(responseMap);
                        responseRoot.set("BOOK", responseNode);
            		}
                	//return error code E001 if book already exists
            		else {
            			//Assume Error code E001 Represents book already exists
            			responseRoot.put("STATUS", "E001");
            		}
            		
            		break;
            		
            	case "UPDATE":
            		//Check if current book entered is existing in current db
            		bookReg = performRetrieveBookByISBN(isbn);
            		if(null != bookReg) {
            			try {
            				//perform update of book detals into DB using data from json payload
            				bookReg = performUpdateBook(bookReg);
            				//bookReg will contain updated book details
            			}
            			catch (Exception e){
            				//print error if encountered
            				e.printStackTrace();
            				//Assume Error code E006 Represents System Error
            				responseRoot.put("STATUS", "E006");
            			}
            			//return success code E000 with book details
                        responseRoot.put("STATUS", "E000");
                        LinkedHashMap<String, String> responseMap = new LinkedHashMap<String, String>();
                        responseMap.put("ISBN", bookReg.getIsbn());
                        responseMap.put("TITLE", bookReg.getTitle());
                        responseMap.put("AUTHORS", bookReg.getAuthorReg());
                        responseMap.put("YEAR", bookReg.getYear());
                        responseMap.put("PRICE", bookReg.getPrice());
                        responseMap.put("GENRE", bookReg.getGenre());
                        
                        JsonNode responseNode = null;
                        responseNode = objectMapper.valueToTree(responseMap);
                        responseRoot.set("BOOK", responseNode);
            		}
                	//return error code E001 if book already exists
            		else {
            			//Assume Error code E002 No such book exists
            			responseRoot.put("STATUS", "E002");
            		}
            		
            		break;
            	case "RETURN":
            		//Check if current book entered is existing in current db using isbn and specific author
            		bookReg = performRetrieveBookByISBNandAuthor(isbn,bookReg.getAuthorReg());
            		//assume there is only 1 copy of each book
            		if(null != bookReg) {
            			if(bookReg.getStock == 0) {
	            			try {
	            				//perform return of book to DB using data from json payload
	            				bookReg = performReturnBook(bookReg);
	            				
	            			}
	            			catch (Exception e){
	            				//print error if encountered
	            				e.printStackTrace();
	            				//Assume Error code E006 Represents System Error
	            				responseRoot.put("STATUS", "E006");
	            			}
	            			//return success code E000 with book details
	                        responseRoot.put("STATUS", "E000");
	                        LinkedHashMap<String, String> responseMap = new LinkedHashMap<String, String>();
	                        responseMap.put("ISBN", bookReg.getIsbn());
	                        responseMap.put("TITLE", bookReg.getTitle());
	                        responseMap.put("AUTHORS", bookReg.getAuthorReg());
	                        responseMap.put("YEAR", bookReg.getYear());
	                        responseMap.put("PRICE", bookReg.getPrice());
	                        responseMap.put("GENRE", bookReg.getGenre());
	                        
	                        JsonNode responseNode = null;
	                        responseNode = objectMapper.valueToTree(responseMap);
	                        responseRoot.set("BOOK", responseNode);
            			}
            			else {
            				//Assume Error code E003 where book is still in stock and not lent out
                            responseRoot.put("STATUS", "E003");
             			}
            		}
            		else {
            			//Assume Error code E002 No such book exists
            			responseRoot.put("STATUS", "E002");
            		}
            		break;
            	case "DELETE":
            		
            		//check if current user is authorized, assume role authorized to delete is "AuthorizedUser"
            		if(userInfo.getRole.equalsIgnoreCase("AuthorizedUser")) {
            			//Check if current book entered is existing in current db
                		bookReg = performRetrieveBookByISBN(isbn);
                		if(null != bookReg) {
                			try {
                				//perform deletion of book record from DB using data from json payload
                				performDeleteBook(bookReg);
                				                			}
                			catch (Exception e){
                				//print error if encountered
                				e.printStackTrace();
                				//Assume Error code E006 Represents System Error
                				responseRoot.put("STATUS", "E006");
                			}
                			//return success code E000 with book details
                			responseRoot.put("STATUS", "E000");
	                        LinkedHashMap<String, String> responseMap = new LinkedHashMap<String, String>();
	                        responseMap.put("ISBN", bookReg.getIsbn());
	                        responseMap.put("TITLE", bookReg.getTitle());
	                        responseMap.put("AUTHORS", bookReg.getAuthorReg());
	                        responseMap.put("YEAR", bookReg.getYear());
	                        responseMap.put("PRICE", bookReg.getPrice());
	                        responseMap.put("GENRE", bookReg.getGenre());
	                        
	                        JsonNode responseNode = null;
	                        responseNode = objectMapper.valueToTree(responseMap);
	                        responseRoot.set("BOOK", responseNode);
                		}
                		else {
                			//Assume Error code E002 No such book exists
                			responseRoot.put("STATUS", "E002");
                		}
            		}
            		else {
            			//Assume Error code E004 Represents UnAuthorized User to delete
            			responseRoot.put("STATUS", "E004");
            		}
            		
            		break;
            	default:
            		//return Error code E010 for unidentified transaction code
            		responseRoot.put("STATUS", "E010");
            		break;
            }
            	
            	
            response = Response //
                    .status(Status.OK) //
                    .type(MediaType.APPLICATION_JSON) //
                    .entity(objectMapper.writeValueAsString(responseRoot)) //
                    .build();
            
            return response;

        }
        catch (JsonProcessingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.fatal(e.getCause());
            logger.fatal(e.getMessage());
        }

        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.fatal(e.getCause());
            logger.fatal(e.getMessage());
        }
        catch (ValidatorException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.fatal(e.getCause());
            logger.fatal(e.getMessage());
        }
    
    }
    
}