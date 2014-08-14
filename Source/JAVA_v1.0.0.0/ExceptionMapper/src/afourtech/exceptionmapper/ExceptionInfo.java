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

/**
 *Represents a class that provides general information about exception 
 * @author AFourTechnologies
 */

public class ExceptionInfo 
{
	//private members of class
	private String message=null;
	private String stackTrace=null;
	private String exceptionType=null;
	private String alternateText=null;
	private String className=null;
	private String statusCode=null;
	

	/**
	 * Gets status code for the call indicating success or failure
	 * @return StatusCode of exception
	 */
	public String getStatusCode() {
		return statusCode;
	}	
	/**
	 * setter for StatusCode field
	 * @param statusCode -StatusCode of exception
	 */
	 void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * Gets the exception message for exception
	 * @return message of exception
	 */
	 public String getExceptionMessage()
	 {
	  return message;
	 }
	 /**
	 * setter for message field
	 * @param message -message of exception
	 */
   	  void setExceptionMessage(String message) 
   	 {
   		 this.message = message;
   	 }
   	/**
 	 * Gets stack trace information about exception 
 	 * @return details of stackTrace
 	 */	
	 public String getStackTrace()
	 {
	  return stackTrace;
	 }
	 /**
	 * setter for stackTrace field
	 * @param stackTraceElements -contains details of stackTrace
	 */	
	  void setStackTrace(String stackTraceElements) 
	{
       	this.stackTrace = stackTraceElements;
	}
	 /**
	 * Gets the type of exception
	 * @return Type of exception
	 */	
	 public String getExceptionType()
	{
		return exceptionType;
	}
	 /**
	 * setter for exceptionType field
	 * @param exceptionType -Type of exception:Functional/Environmental/Other
	 */		 
	  void setExceptionType(String exceptionType)
	{
		this.exceptionType=exceptionType;
	}
	 /**
	 * Gets alternate text for the exception  
	 * @return AlternateText of XML Tag
	 */
	 public String getAlternateText()
	{
		return alternateText;
	}
	 /**
	 * setter for alternateText field
	 * @param alternateText - alternate text of XML tag
	 */	
	  void setAlternateText(String alternateText)
	{
		this.alternateText=alternateText;
	}
	  
	 /**
	 * Gets base class name where exception occurred.
	 * @return Name of exception class
	 */	
	 public String getClassName()
	{
		return className;
	}
	 /**
	 * setter for className field
	 * @param className - Name of Exception Class
	 */	
	  void setClassName(String className)
	{
		this.className=className;
	}
	
	
}
