/*  
Copyright 2014 AFour Technologies

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/

package afourtech.exceptionmapper;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.*;
/**
 *  Mapper Class represents a class that returns description of exception in form of ExceptionInfo Object
 * @author AFourTechnologies
 */
public class Mapper
{
  private static String xmlFilePath;
  /**
	* Specify the file path where ExceptionMapping.xml is located.
	* @return xmlFilePath of XMLfile
    */
  public static  String getXmlFilePath()
  {
	  return xmlFilePath;
  }
  /**
    * Specify the file path where ExceptionMapping.xml is located.
    * @param xmlFilePath -path for XML file
 	* @return void
 	*/		 
  static void setXmlFilePath(String FilePath)
  {
	  xmlFilePath=FilePath;
  }
    
  /**
	*This Hashtable is used for one time caching of xml file.
    */	
  private static Hashtable<String, Hashtable<String , String>> exceptionTypeHashtable;
  
  /**
 	* This Hashatble is used to store messages tag from xml file.
 	*/
  private static Hashtable<String, String> messageHashtable=null;
  
  private static String exceptionType;
  private static String alternateText;
  private static String defaultExceptionType;
  private static ExceptionInfo exceptionInfo;

  /**
   *   Status code for current error.
   */
  private static int statusCodeDictionaryFinder = 100;
  
  /**
   *   Dictionary for checking status of our errors with status code and messages.
   */
  private static Dictionary<Integer , String> statusCodeDictionary= null;
 /**
  *  Method to initialize Error codes in dictionary
  */
  private static void initializeErrorCodeDict()
  {
   statusCodeDictionary = new Hashtable<Integer, String>();	  
   // add elements in the Dictionary
   statusCodeDictionary.put(100, "Success");
   statusCodeDictionary.put(101, "An Error has occurred in our library please contact AFourTech for further Information on debugging this error.");
   statusCodeDictionary.put(103, "Syntax Error in file. Please follow proper syntax");  
  }
  
  /**
   * Enum to find StepCondition type 
   */  
  private enum StepConditionEnum
  {
      GIVEN, WHEN
  }
    
  /**
   * Method to cache xml file to Hashtable
   */ 
  private static void cacheXMLDocument()
  {
	  initializeErrorCodeDict();
	  if(getXmlFilePath()==null)
	  {
		  /**
		   * Get the XML file path from current Directory
		   */
	 	  String xmlPath=System.getProperty("user.dir");
	      setXmlFilePath(xmlPath+"\\resources\\ExceptionMapping.xml");
	  }
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();	  	    
	  DocumentBuilder builder;
		    try {
		    	
		     	statusCodeDictionary.put(102, "File Could not be found at '" + xmlFilePath + "'");
		     	exceptionTypeHashtable =new Hashtable<String, Hashtable<String, String>>();
		    	builder = factory.newDocumentBuilder();					  	 
	            // Load the input XML document, parse it and return an instance of the
	  	        // Document class.
	  	        Document document = builder.parse(xmlFilePath);	  	      	  	       
	  	        NodeList nodeList = document.getDocumentElement().getChildNodes();
	  	        for (int i = 0; i < nodeList.getLength(); i++) 
  	            {  	        	   	    	    	   
	    	       String alternateTextValue= null;
	    	       String messageValue=null;
	    	       String parentAttributeValue=null;
	    	       String parentNodeName=null;	    	  	         
  	        	   Node parentExceptionNode = nodeList.item(i);	  
	  	           if(parentExceptionNode.getNodeType()== Node.ELEMENT_NODE)
	  	           {	  	       
	  	           	   parentNodeName=parentExceptionNode.getNodeName();	  	        	   
	  	    	       int length = (parentExceptionNode.getAttributes() != null) ? parentExceptionNode.getAttributes().getLength() : 0;
	  	    	    	Attr attributes[] = new Attr[length];
	  	    	    	for (int loopIndex = 0; loopIndex < length; loopIndex++)
	  	    	    	{
	  	    	    	    attributes[loopIndex] = (Attr)parentExceptionNode.getAttributes().item(loopIndex);
	  	    	    	}
	  	    	    	for (int loopIndex = 0; loopIndex < attributes.length; loopIndex++) 
	  	    	    	{
	  	    	    	    Attr attribute = attributes[loopIndex];	  	    	    	 
	  	    	    	  	  	    	    	
	  	    	    	    parentAttributeValue= attribute.getNodeValue();	  	    	    		  	    	    
	  	    	    	}
	  	    	    	
	  	    	    	//-----------------child node------------
	  	    	    	 messageHashtable= new Hashtable<String, String>();
	  	    	    	 NodeList childNodes = parentExceptionNode.getChildNodes();	  	    	    	
	  	  	             for (int j = 0; j < childNodes.getLength(); j++) 
	  	  	             {
	  	  	            	 Node childNodeOfNode =childNodes.item(j);	  	        
	  	  	                 if("message".equalsIgnoreCase(childNodeOfNode.getNodeName()))
	  		  	             {
	  	  	                	
	  		  	    	        int len = (childNodeOfNode.getAttributes() != null) ?childNodeOfNode.getAttributes().getLength() : 0;	  		  	    	    		
	  		  	    	    	Attr attributes1[] = new Attr[len];
	  		  	    	    	if(len > 0)
	  		  	    	    	{
	  		  	    	    		for (int loopIndex = 0; loopIndex < len; loopIndex++)
	  		  	    	    		{
	  		  	    	    			attributes1[loopIndex] = (Attr)childNodeOfNode.getAttributes().item(loopIndex);
	  		  	    	    		}
	  		  	    	    		for (int loopIndex = 0; loopIndex < attributes1.length; loopIndex++) 
	  		  	    	    		{
	  		  	    	    			Attr attribute = attributes1[loopIndex];	  		  	    	    	   	  		  	    	    			  	    	    	   
	  		  	    	    			alternateTextValue= attribute.getNodeValue();
	  		  	    	    			messageValue=childNodeOfNode.getTextContent();
	  		  	    	    			messageHashtable.put(messageValue, alternateTextValue);
	  		  	    	    		}
	  		  	    	    	}
	  		  	    	    	else
	  		  	    	    	{
	  		  	    	    		 messageValue=childNodeOfNode.getTextContent();	  		  	    	 	       
	  		  	    	 	         alternateTextValue="";
	  		  	    	 	         messageHashtable.put(messageValue, alternateTextValue);
	  		  	        	    }	  		  	    	    
	  		  	             }
	  	  	             }	 	
	  	  	             if(parentNodeName.equalsIgnoreCase("OtherException"))// when exception not present in file then set exceptiontype to otherException.
	  	  	             {	  	  	            
	  	  	            	defaultExceptionType=parentAttributeValue;	  	  	       	
	  	  	             }	  	  	             
	  	  	         exceptionTypeHashtable.put(parentAttributeValue, messageHashtable);
	  	          }		  	       
  	            }      
	  	          
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block					
					  statusCodeDictionaryFinder = 102;
				} catch (SAXParseException e) {
					// TODO Auto-generated catch block
					statusCodeDictionaryFinder = 103;
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					statusCodeDictionaryFinder = 101;
				} catch (IOException e) {					
					// TODO Auto-generated catch block
					statusCodeDictionaryFinder = 101;
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					statusCodeDictionaryFinder = 101;
				}		   
  }
   
    
  /**
 * getExceptionInfo
 * <br><br>
 *{@code public ExceptionInfo getExceptionInfo(Exception exception, String stepString)} 
 * <br>
 * <br>
 * Function to return information about Exception given by user with step level condition provided as parameter.
 * @param exception object of Exception class
 * @param stepString   String such as Given ,When ,Then and And
 * @return object of ExceptionInfo class.
 */
  public static ExceptionInfo getExceptionInfo(Exception exception, String stepString)
  {	
	  exceptionInfo =new ExceptionInfo();
	  exceptionType=null;
	  int  WhenGivenConditionChecker=0; 
	  int flag=0;
	  String stackData=null;
	  if (exceptionTypeHashtable == null)
	  {
		  cacheXMLDocument();
	  }  
	  if(stepString!=null)
	  {					 	
		  if (stepString.toUpperCase().equalsIgnoreCase(StepConditionEnum.GIVEN.toString()) || stepString.toUpperCase().equalsIgnoreCase(StepConditionEnum.WHEN.toString()))
		  {
			  exceptionType="Environmental";		
			  WhenGivenConditionChecker=1;			 
		  }
	  }
	  try
	  {			
		  if (!exceptionTypeHashtable.isEmpty())
		  {   		  					
				  exceptionType = defaultExceptionType;          
	              alternateText = "";
                  //Traverse HashTable to get Exception Data present in xml file.     	
				 for(String exceptionRootElement : exceptionTypeHashtable.keySet())
				 {			  	        
				    if(flag==0)
				    {
				      		messageHashtable=new Hashtable<String, String>();
				      		messageHashtable=exceptionTypeHashtable.get(exceptionRootElement);
				  	        for(String exceptionMessage : messageHashtable.keySet())
				  	        {
		                            String alternetTextOfException = messageHashtable.get(exceptionMessage);                         	                       
		                            if ((exception.getMessage().equalsIgnoreCase(exceptionMessage)))
		                            {                            	                            		                           
		                            	exceptionType = exceptionRootElement;     
                                       
	                                    if ("Functional".equalsIgnoreCase(exceptionType) && WhenGivenConditionChecker == 1)
	                                    {
	                                        alternateText = "";	          	                    
	                                    }
	                                    else
	                                    {
	                                        alternateText = alternetTextOfException;
	                                    }            
	                                    if(WhenGivenConditionChecker==1)
	                                    {
	                                    	exceptionType="Environmental";
	                                    }
		                                flag=1;
		                                 break;
		                            }                           
		                     }
				      }			      
				  	  else
				  	  {
				  	        break;
				  	  }
				  }
				      				 	  
				 if(alternateText==null|| alternateText=="")
				 {
					 exceptionInfo.setAlternateText("");
				
				 } 
				 //to read stackTrace into a String.
				 int length =exception.getStackTrace().length;
			 
				 String newLine=System.getProperty("line.separator");
				 for(int i=0;i<length;i++)
				 {
					 stackData=stackData+exception.getStackTrace()[i].toString()+newLine;
				 }
		  	  //adding details to the ExceptionInfo Object
		  }

		  exceptionInfo.setExceptionMessage(exception.getMessage().toString());
		  exceptionInfo.setClassName(exception.getClass().getSimpleName());	
		  exceptionInfo.setAlternateText(alternateText);
		  exceptionInfo.setStackTrace(stackData);
		}
	  	catch (NullPointerException e) {
	  		statusCodeDictionaryFinder = 101; 
	  	}
		catch(Exception e)
		{
		  			statusCodeDictionaryFinder = 101; 
		}		     
	    exceptionInfo.setExceptionType(exceptionType);
	    exceptionInfo.setStatusCode( statusCodeDictionaryFinder + " - " + statusCodeDictionary.get(statusCodeDictionaryFinder));
	    writeStackInfo(exceptionInfo.getStackTrace());	  
	    return exceptionInfo;
  }
   
  
  /**
  * getExceptionInfo
  * <br><br>
  *{@code public ExceptionInfo getExceptionInfo(Exception exception)} 
  * <br>
  * <br>
  * Function to return information about Exception given by user
  * @param exception object of Exception class
  * @return object of ExceptionInfo class.
  */
  public static ExceptionInfo getExceptionInfo(Exception exception)
  {	
	  exceptionInfo =new ExceptionInfo();

	  int flag=0;
	  String stackData=null;
	  if (exceptionTypeHashtable == null)
	  {
		  cacheXMLDocument();
	  }   
	  try
	  {	
		  if (!exceptionTypeHashtable.isEmpty())
		  {   		  	  
				  exceptionType = defaultExceptionType;          
	              alternateText = "";
                  //Traverse HashTable to get Exception Data present in xml file.     	
				 for(String exceptionRootElement : exceptionTypeHashtable.keySet())
				 {			  	        
				    if(flag==0)
				    {
				      		messageHashtable=new Hashtable<String, String>();
				      		messageHashtable=exceptionTypeHashtable.get(exceptionRootElement);
				  	        for(String exceptionMessage : messageHashtable.keySet())
				  	        {
		                            String alternetTextOfException = messageHashtable.get(exceptionMessage);                         	                       
		                            if ((exception.getMessage().equalsIgnoreCase(exceptionMessage)))
		                            {                            	                            		                           
		                            	exceptionType = exceptionRootElement;                                       
	                                    alternateText = alternetTextOfException;	                                                                   	                               
		                                flag=1;
		                                 break;
		                            }                           
		                     }
				      }			      
				  	  else
				  	  {
				  	        break;
				  	  }
				  }
				      				 	  
				 if(alternateText==null|| alternateText=="")
				 {
					 exceptionInfo.setAlternateText("");
				
				 }					 
				 //to read stackTrace into a String.
				 int length =exception.getStackTrace().length;			 
				 String newLine=System.getProperty("line.separator");
				 for(int i=0;i<length;i++)
				 {
					 stackData=stackData+exception.getStackTrace()[i].toString()+newLine;
				 }
		  	  //adding details to the ExceptionInfo Object
		  }
		  exceptionInfo.setExceptionMessage(exception.getMessage().toString());
		  exceptionInfo.setClassName(exception.getClass().getSimpleName());	
		  exceptionInfo.setExceptionType(exceptionType);
		  exceptionInfo.setAlternateText(alternateText);
		  exceptionInfo.setStackTrace(stackData);
		}
	  	catch (NullPointerException e) {
	  	}
		catch(Exception e)
		{
		  			statusCodeDictionaryFinder = 101; ;
		}		     
	    exceptionInfo.setStatusCode( statusCodeDictionaryFinder + " - " + statusCodeDictionary.get(statusCodeDictionaryFinder));
		writeStackInfo(exceptionInfo.getStackTrace());  
		return exceptionInfo;
  }
  
  /**
  * writeStackInfo
  * <br><br>
  *{@code public void writeStackInfo(String stackTrace)} 
  * <br>
  * <br>
  * Function to write stackTrace details of an exception  into log file
  * Takes String type argument 
  * @param stackTraceElements contains details of stack trace
  *
  * @return void
  */
  private static void writeStackInfo(String stackTraceElements)
  {
	  try
	  {
		  DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd hh mm ss SSS ");		 
		  Date date = new Date();
		  String time=dateFormat.format(date); 
		  String folderName="StackLog";		  
		  String parentDirName=System.getProperty("user.dir");
		  String subDirectoryName= parentDirName + "\\" +  folderName;
		  File stackLogDir=new File(subDirectoryName); 
		  
		  if(!stackLogDir.exists())
		  {
			  stackLogDir.mkdir();
		  }	
		  String logFileName=subDirectoryName+"\\StackTrace_"+time+".log";
		  File file=new File(logFileName);
		  file.createNewFile();		  
		  PrintWriter writer = new PrintWriter(logFileName, "UTF-8");
		  writer.println(stackTraceElements);
		  writer.close();
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
  }
}
