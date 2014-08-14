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

package exceptionMapApp;
import afourtech.exceptionmapper.*;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
 
       
		//XmlFilePath : Optional path, If not set takes resource directory path in project folder for ExceptionMapping.xml by default.
        //Mapper.XmlFilePath = "D:/ExceptionMapping.xml";
		
   try
        {
            int a = 4;
            int b = 0;
            int div = a / b;
            System.out.println("Result  == " + div);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei =Mapper.getExceptionInfo(ex);
            ShowExceptionResult(ei);
        }
        
        //---------------------------------------------------------
   /*       try
        {
            int a = 4;
            int b = 0;
            int div = a / b;
            System.out.println("Result  == " + div);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei =Mapper.getExceptionInfo(ex, "then");
            ShowExceptionResult(ei);
        }
        //---------------------------------------------------------
        try
        {
            int a = 4;
            int b = 0;
            int div = a / b;
            System.out.println("Result  == " + div);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei =Mapper.getExceptionInfo(ex, "given");
            ShowExceptionResult(ei);
        }
        //---------------------------------------------------------
        try
        {
            int a = 4;
            int b = 0;
            int div = a / b;
            System.out.println("Result  == " + div);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei =Mapper.getExceptionInfo(ex, "when");
            ShowExceptionResult(ei);
        }
        //---------------------------------------------------------
   
        try
        {
            int a = 4;
            int b = 0;
            int div = a / b;
            System.out.println("Result  == " + div);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei =Mapper.getExceptionInfo(ex, "sss");
            ShowExceptionResult(ei);
        }
        //---------------------------------------------------------
        
        try
        {
            int a = 4;
            int b = 0;
            int div = a / b;
            System.out.println("Result  == " + div);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei =Mapper.getExceptionInfo(new Exception("NullReferenceException"), "sss");
            ShowExceptionResult(ei);
        }*/
        //---------------------------------------------------------
        //---------------------------------------------------------


        try
        {
            int[] x = { 10, 20, 30 };
            int abc = x[4];
            System.out.println("Result  == " + abc);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei =Mapper.getExceptionInfo(new Exception("Index was outside the bounds of the array."));
            ShowExceptionResult(ei);
        }
//---------------------------------------------------------
        try
        {
            int[] x = { 10, 20, 30 };
            int abc = x[4];
            System.out.println("Result  == " + abc);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei = Mapper.getExceptionInfo(new Exception("Index was outside the bounds of the array."), "then");
   //         ExceptionInfo ei = Mapper.GetExceptionInfo(new Exception("InvaliArgumentException"), "when");
     //      ExceptionInfo ei =Mapper.getExceptionInfo(new Exception("InvaliArgument"), "when");
            ShowExceptionResult(ei);
        }
//---------------------------------------------------------
        try
        {
            int[] x = { 10, 20, 30 };
            int abc = x[4];
            System.out.println("Result  == " + abc);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei = Mapper.getExceptionInfo(new Exception("Index was outside the bounds of the array."), "when");
   //         ExceptionInfo ei = Mapper.GetExceptionInfo(new Exception("InvaliArgumentException"), "when");
     //      ExceptionInfo ei =Mapper.getExceptionInfo(new Exception("InvaliArgument"), "when");
            ShowExceptionResult(ei);
        }
//---------------------------------------------------------
        try
        {
            int[] x = { 10, 20, 30 };
            int abc = x[4];
            System.out.println("Result  == " + abc);

        }
        catch (Exception ex)
        {
            ExceptionInfo ei = Mapper.getExceptionInfo(new Exception("Index was outside the bounds of the array."), "given");
   //         ExceptionInfo ei = Mapper.GetExceptionInfo(new Exception("InvaliArgumentException"), "when");
     //      ExceptionInfo ei =Mapper.getExceptionInfo(new Exception("InvaliArgument"), "when");
            ShowExceptionResult(ei);
        }
//---------------------------------------------------------
        try
        {
            int[] x = { 10, 20, 30 };
            int abc = x[4];
            System.out.println("Result  == " + abc);

        }
        catch (Exception ex)
        {
     //       ExceptionInfo ei = Mapper.GetExceptionInfo(ex, "then");
    //       ExceptionInfo ei = Mapper.GetExceptionInfo(new Exception("InvaliArgumentException"), "then");
            ExceptionInfo ei =Mapper.getExceptionInfo(new Exception("InvaliArgumentException"), "then");
            ShowExceptionResult(ei);
        }
//---------------------------------------------------------
        try
        {
            int[] x = { 10, 20, 30 };
            int abc = x[4];
            System.out.println("Result  == " + abc);

        }
        catch (Exception ex)
        {
    //      ExceptionInfo ei = Mapper.GetExceptionInfo(ex, "given");
      //     ExceptionInfo ei = Mapper.GetExceptionInfo(new Exception("InvaliArgumentException"), "given");
         ExceptionInfo ei =Mapper.getExceptionInfo(new Exception("InvaliArgumentException"), "given");
            ShowExceptionResult(ei);
        }
//---------------------------------------------------------
        try
        {
            int[] x = { 10, 20, 30 };
            int abc = x[4];
            System.out.println("Result  == " + abc);

        }
        catch (Exception ex)
        {
        //    ExceptionInfo ei =Mapper.getExceptionInfo(new Exception("InvaliArgument"), "when");
            ExceptionInfo ei = Mapper.getExceptionInfo(new Exception("Index was outside the bounds of the array."), "ggg");
            ShowExceptionResult(ei);
        }
      
        //   ExceptionInfo v =Mapper.getExceptionInfo(new Exception("fds"),"gi");

	}
    public static void ShowExceptionResult(ExceptionInfo exc)
    {
        System.out.println("Status Code    --" + exc.getStatusCode() + "\n");
        System.out.println("Message        --" + exc.getExceptionMessage());
        System.out.println("Class name     --" + exc.getClassName());
        System.out.println("Exception Type --" + exc.getExceptionType());
        System.out.println("AlternateText  --" + exc.getAlternateText());
        System.out.println("StackTrace     --" + exc.getStackTrace());
        System.out.println("---------------------------------------------------------------------------------\n");
    }
	
	
}

	
	


