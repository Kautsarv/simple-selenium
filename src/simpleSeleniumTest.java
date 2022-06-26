import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class simpleSeleniumTest {
    public static void main(String[] args){

        //Create Chromedriver Object
        System.setProperty("webdriver.chrome.driver","./webdriver/chromedriver");   //Set path to Chromedriver manually
        ChromeDriver driver = new ChromeDriver();                                   //instantiate Chromedriver

        //Open Browser and enter the URL
        driver.get("https://katalon-demo-cura.herokuapp.com/");

        //Assert website title header with expected title
        String title = driver.getTitle();                   //Get website title
        String expectedtitle = "CURA Healthcare Service";   //Expected title
        if (title.contentEquals(expectedtitle)) {           //Create condition for checking whether title already correct or not
            System.out.println("Title Match!");
        }
        else {
            System.out.println("Title Doesn't Match");
        }

        //User click the Appointment button
        WebElement buttonAppointment = driver.findElement(By.id("btn-make-appointment"));   //Create Appointment button object with ID
        buttonAppointment.click();                                                          //Click Appointment button

        //Verify login page is displayed
        String loginurl = driver.getCurrentUrl();                                               //Get the current URL
        if (loginurl.equals("https://katalon-demo-cura.herokuapp.com/profile.php#login")) {     //Create condition for checking whether Login URL already correct or not
            System.out.println("Login URL correct!");
        }
        else {
            System.out.println("Incorrect Login URL!");
        }
        WebElement labelLogin = driver.findElement(By.xpath("//*[@id='login']/div/div/div[1]/h2"));    //Create Login label object with XPATH
        String loginText = labelLogin.getText();                                                       //Get login label text
        if (loginText.equals("Login")) {                                                               //Create condition for checking whether Login text already correct or not
            System.out.println("Login Page Opened!");
        }
        else {
            System.out.println("Login Page Not Found!");
        }

        //User login with username and password
        WebElement inputUsername = driver.findElement(By.id("txt-username"));    //Create Username input field object by ID
        inputUsername.sendKeys("John Doe");                         //Input text into Username field
        WebElement inputPassword = driver.findElement(By.id("txt-password"));    //Create Password input field object by ID
        inputPassword.sendKeys("ThisIsNotAPassword");               //Input text into Password field
        WebElement buttonLogin = driver.findElement(By.id("btn-login"));         //Create Login button object by ID
        buttonLogin.click();                                                     //Click Login button

        //Verify appointment page is displayed
        String appointmenturl = driver.getCurrentUrl();                                               //Get the current URL
        if (appointmenturl.equals("https://katalon-demo-cura.herokuapp.com/#appointment")) {          //Create condition for checking whether appointment URL already correct or not
            System.out.println("Appointment URL correct!");
        }
        else {
            System.out.println("Incorrect Appointment URL!");
        }
        WebElement labelAppointment = driver.findElement(By.xpath("//*[@id='appointment']/div/div/div/h2"));    //Create Appointment label object with XPATH
        String appointmentText = labelAppointment.getText();                                                    //Get Appointment label text
        if (appointmentText.equals("Make Appointment")) {                                                       //Create condition for checking whether Appointment text already correct or not
            System.out.println("Appointment Page Opened!");
        }
        else {
            System.out.println("Appointment Page Not Found!");
        }

        //User select facility droplist
        Select drpFacility = new Select(driver.findElement(By.id("combo_facility")));             //Create Facility droplist object by ID
        drpFacility.selectByValue("Hongkong CURA Healthcare Center");                             //Select Facility droplist by value
        String selectedValue = drpFacility.getWrappedElement().getDomProperty("value");     //Get the selected droplist value based on DOM property

        //User checklist hospital readmission checkbox
        WebElement chkAdmission = driver.findElement(By.id("chk_hospotal_readmission"));    //Create Readmission checkbox object by ID
        chkAdmission.click();                                                               //Click Readmission checkbox
        String chkAdmissionFlag;                                                            //Create Admission flag variable
        if(chkAdmission.isSelected()){                                                      //Get the checkbox checked state based on DOM property
            chkAdmissionFlag = "Yes";
        }
        else{
            chkAdmissionFlag = "No";
        }

        //User select healthcare program radiobutton
        WebElement radioMedicaid = driver.findElement(By.id("radio_program_medicaid"));     //Create Healthcare Program radiobutton object by ID
        radioMedicaid.click();                                                              //Click Healthcare Program radiobutton
        String radioMedicaidText = radioMedicaid.getDomProperty("value");             //Get the radiobutton value based on DOM property

        //User select visit date
        WebElement buttonCalendar = driver.findElement(By.id("txt_visit_date"));                                                    //Create Visit Calendar button object by ID
        buttonCalendar.click();                                                                                                     //Click Visit Calendar
        List<WebElement> date = driver.findElements(By.xpath("//td[@class='day']"));                                                //Create element lists of date object to get the maximum date on a month
        int randomDate = ThreadLocalRandom.current().nextInt(1, date.size() + 1);                                      //Create variable for input random date between 1-maximum date in a month
        WebElement selectDate = driver.findElement(By.xpath("//td[@class='day' and normalize-space(text())='"+randomDate+"']"));    //Create date object based on inputted number in variable randomDate
        selectDate.click();                                                                                                         //Click date
        String selectedDate = buttonCalendar.getDomProperty("value");                                                         //Get the date value based on DOM property

        //User input comment
        WebElement inputComment = driver.findElement(By.id("txt_comment"));       //Create Comment input object by ID
        inputComment.sendKeys("Test Selenium");                      //Input text into Comment field
        String inputtedComment = inputComment.getDomProperty("value");     //Get the inputted Comment value based on DOM property

        //User submit appointment
        WebElement buttonBook = driver.findElement(By.id("btn-book-appointment"));     //Create Appointment button object by ID
        buttonBook.click();                                                            //Click Appointment button

        //Verify appointment confirmation page displayed
        WebElement labelConfirmation = driver.findElement(By.xpath("//*[@id='summary']/div/div/div[1]/h2"));  //Create Appointment Confirmation label object with XPATH
        String confirmationText = labelConfirmation.getText();                                                //Get Appointment Confirmation label text
        if (confirmationText.equals("Appointment Confirmation")) {                                            //Create condition for checking whether Appointment Confirmation text already correct or not
            System.out.println("Appointment Confirmation Opened!");
        }
        else {
            System.out.println("Appointment Confirmation Not Found!");
        }

        //Assert appointment confirmation data with expected result based on inputted data on previous page
        WebElement labelFacility = driver.findElement(By.id("facility"));                     //Create Facility text object with ID
        WebElement labelReadmission = driver.findElement(By.id("hospital_readmission"));      //Create Readmission text object with ID
        WebElement labelProgram = driver.findElement(By.id("program"));                       //Create Healthcare Program text object with ID
        WebElement labelVisitDate = driver.findElement(By.id("visit_date"));                  //Create Visit Date text object with ID
        WebElement labelComment = driver.findElement(By.id("comment"));                       //Create Comment text object with ID
        System.out.println("Facility : "+selectedValue);
        System.out.println("Hospital Readmission : "+chkAdmissionFlag);
        System.out.println("Healthcare Program : "+radioMedicaidText);
        System.out.println("Visit Date : "+selectedDate);
        System.out.println("Comment : "+inputtedComment);
        if (labelFacility.getText().equals(selectedValue)                                     //Create condition for checking whether all displayed fields are identics with the inputted data
            && labelReadmission.getText().equals(chkAdmissionFlag)
            && labelProgram.getText().equals(radioMedicaidText)
            && labelVisitDate.getText().equals(selectedDate)
            && labelComment.getText().equals(inputtedComment)) {
            System.out.println("Test Passed!");
        }
        else {
            System.out.println("Test Failed!");
        }

        //Close browser
        driver.close();
    }
}
