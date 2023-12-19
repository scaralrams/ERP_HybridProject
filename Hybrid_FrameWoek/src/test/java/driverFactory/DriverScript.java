package driverFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibraray;
import utils.ExcelUtils;

public class DriverScript {
    public WebDriver driver;
    String inputxl = "./FileInput/Controller-TC.xlsx";
    String outputxl = "./FileOutput/Results.xlsx";
    String MasterSheetName = "TestCases";
   ExtentReports extentReports;
   ExtentTest Test;

    public void LogIn$LogOut() throws Throwable 
    {
        ExcelUtils xl = new ExcelUtils(inputxl);
        int masterSheetRowCount = xl.RowCount(MasterSheetName);
        

        for (int i = 1; i <= masterSheetRowCount; i++) 
        {
            String ExecutionStatus = xl.getcellData(MasterSheetName, i, 2);
            String ModuleName = xl.getcellData(MasterSheetName, i, 1);
            if (ExecutionStatus.equalsIgnoreCase("yes")) 
            {
            	String  date=FunctionLibraray.getDate();
            	extentReports=new ExtentReports("./target/Reports/"+ModuleName+date+".html");
            	extentReports.addSystemInfo("OS_Vesrion :-", System.getProperty("os.name"));
        		extentReports.addSystemInfo("Java_Vesrion :-", System.getProperty("java.version"));
        		Test=extentReports.startTest(ModuleName);
                int ModuleSheetRowCount = xl.RowCount(ModuleName);
                boolean moduleStatus = true;

                for (int j = 1; j <= ModuleSheetRowCount; j++) 
                {
                	
                    String Description = xl.getcellData(ModuleName, j, 0);
                    String Object_type = xl.getcellData(ModuleName, j, 1);
                    String Locator_Type = xl.getcellData(ModuleName, j, 2);
                    String Locator_Value = xl.getcellData(ModuleName, j, 3);
                    String Test_Data = xl.getcellData(ModuleName, j, 4);
                    try {
                        if (Object_type.equalsIgnoreCase("openbrowser"))
                        {
                            driver = FunctionLibraray.openBrowser();
                            Thread.sleep(2000);
                            Capabilities cap=((RemoteWebDriver)driver).getCapabilities();
                            extentReports.addSystemInfo("Browser_Name:-", cap.getBrowserName());
                            extentReports.addSystemInfo("Browser_Version:-", cap.getBrowserName());
                           Test.log(LogStatus.PASS, Description);
                         
                            
                            
                        }
                        if (Object_type.equalsIgnoreCase("enterURL")) 
                        {
                            FunctionLibraray.enterURL(driver);
                            Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("waitforelement"))
                        {
                            FunctionLibraray.waitforElement(driver, Locator_Type,Locator_Value, Test_Data);
                            Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("typeaction")) 
                        {
                            FunctionLibraray.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
                            Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("clickaction"))
                        {
                            FunctionLibraray.clickAction(driver, Locator_Type, Locator_Value);
                            Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("dataValidation")) 
                        {
                          FunctionLibraray.dataValidation(driver, Test_Data);
                          Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("closebrowser"))
                        {
                            FunctionLibraray.closeBrowser(driver);
                            Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("movemouse"))
                        {
                        	FunctionLibraray.moveMouse(driver, Locator_Type, Locator_Value);
                            Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("movemouseandclick"))
                        {
                        	FunctionLibraray.moveMouseAndClick(driver, Locator_Type, Locator_Value);
                            Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("searchbardisplay"))
                        {
                            FunctionLibraray.searchBarDisplay(driver, Locator_Type, Locator_Value);
                            Test.log(LogStatus.PASS, Description);

                        }
                        if (Object_type.equalsIgnoreCase("gettingText"))
                        {
                            FunctionLibraray.gettingText(driver, Locator_Value, Test_Data);
                            Test.log(LogStatus.PASS, Description);

                        }
                        
                        xl.setCellData(ModuleName, j, 5, "PASS", outputxl);
                    } catch (Throwable e) 
                    {
                        xl.setCellData(ModuleName, j, 5, "FAIL", outputxl);
                        moduleStatus = false;
                        Test.log(LogStatus.FAIL, Description);

                        System.out.println(e.getMessage());

                        
                    }
                   
                }
                if (moduleStatus) 
                {
                    xl.setCellData(MasterSheetName, i, 3, "PASS", outputxl);
                } else 
                {
                    xl.setCellData(MasterSheetName, i, 3, "FAIL", outputxl);
               
                }
                extentReports.endTest(Test);
               extentReports.flush();
            }
            
            else 
            {
                xl.setCellData(MasterSheetName, i, 3, "Blocked", outputxl);
            }
        }
    }
}
