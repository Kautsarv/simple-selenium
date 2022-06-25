import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static void main(String[] args){
        //Membuat objek Chromedriver
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Membuka browser dan masuk ke URL
        driver.get("https://katalon-demo-cura.herokuapp.com/");

        //Mengambil title suatu website
        String title = driver.getTitle();
        //Membandingkan title website yang didapat dengan ekspektasi title yang kita berikan
        String expectedtitle = "CURA Healthcare Service";
        if (title.contentEquals(expectedtitle)) {
            System.out.println("Test Passed!");
        }
        else {
            System.out.println("Test Failed");
        }

        //Membuat objek button appointment
        WebElement buttonAppointment = driver.findElement(By.id("btn-make-appointment"));
        //Melakukan click pada objek button
        buttonAppointment.click();

        //Membuat objek label login
        WebElement labelLogin = driver.findElement(By.xpath("//*[@id='login']/div/div/div[1]/h2"));
        //Mengambil teks pada objek yang dituju dan membandingkan dengan ekspektasi teks yang kita berikan
        String loginText = labelLogin.getText();
        if (loginText.equals("Login")) {
            System.out.println("Test Passed!");
        }
        else {
            System.out.println("Test Failed");
        }

        //Membuat objek input username
        WebElement inputUsername = driver.findElement(By.id("txt-username"));
        //Input text pada objek input username
        inputUsername.sendKeys("John Doe");
        //Membuat objek input password
        WebElement inputPassword = driver.findElement(By.id("txt-password"));
        //Input text pada objek input username
        inputPassword.sendKeys("ThisIsNotAPassword");

        //Membuat objek button login
        WebElement buttonLogin = driver.findElement(By.id("btn-login"));
        //Melakukan click pada objek button
        buttonLogin.click();

        Select drpFacility = new Select(driver.findElement(By.id("combo_facility")));
        WebElement drpFacilityValue = driver.findElement(By.id("combo_facility"));
        drpFacility.selectByValue("Hongkong CURA Healthcare Center");
        String selectedValue = drpFacilityValue.getDomProperty("value");

        WebElement chkAdmission = driver.findElement(By.id("chk_hospotal_readmission"));
        chkAdmission.click();
        String chkAdmissionFlag;
        if(chkAdmission.getDomProperty("checked") == "true"){
            chkAdmissionFlag = "Yes";
        }
        else{
            chkAdmissionFlag = "No";
        }

        WebElement radioMedicaid = driver.findElement(By.id("radio_program_medicaid"));
        radioMedicaid.click();
        String radioMedicaidText = radioMedicaid.getDomProperty("value");

        WebElement btnCalendar = driver.findElement(By.id("txt_visit_date"));
        btnCalendar.click();
        WebElement selectDate = driver.findElement(By.xpath("(//td[normalize-space(text())='4'])[1]"));
        selectDate.click();
        String selectedDate = btnCalendar.getDomProperty("value");

        WebElement txtComment = driver.findElement(By.id("txt_comment"));
        txtComment.sendKeys("Test Selenium");
        String inputtedComment = txtComment.getDomProperty("value");

        WebElement btnBook = driver.findElement(By.id("btn-book-appointment"));
        btnBook.click();

        WebElement lblFacility = driver.findElement(By.id("facility"));
        WebElement lblReadmission = driver.findElement(By.id("hospital_readmission"));
        WebElement lblProgram = driver.findElement(By.id("program"));
        WebElement lblVisitDate = driver.findElement(By.id("visit_date"));
        WebElement lblComment = driver.findElement(By.id("comment"));

        if (lblFacility.getText().equals(selectedValue)
            && lblReadmission.getText().equals(chkAdmissionFlag)
            && lblProgram.getText().equals(radioMedicaidText)
            && lblVisitDate.getText().equals(selectedDate)
            && lblComment.getText().equals(inputtedComment)) {
            System.out.println("Test Passed Semua");
        }
        else {
            System.out.println("Test Failed");
        }

        //menutup browser
        driver.close();
    }
}
