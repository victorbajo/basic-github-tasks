import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/** 1. Visit the konga URL
 2. Sign in to Konga succesfully
 3. From categories, click on the computers and accessories
 4. Click on the laptop subcategory
 5. Click on the Apple Macbooks
 6. Add an item to the cart
 7. Select Address
 8. Continue to make payment
 9. Select a card Payment method
 10. Input invalid card details
 11. Print out the error message: Invalid card Number
 12. Close the Iframe that displays the input card Modal
 12. Quit the browser

 */


public class KongaTest {


    private WebDriver driver;

    @BeforeTest

    public void start() throws InterruptedException {

        //locate the chromedriver

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");

        // Open your chrome browser

        driver = new ChromeDriver();

        //1. Visit the Konga Url
        driver.get("https://www.konga.com/");
        //Test1. Verify the user input the right url and is on the right webpage
        if (driver.getCurrentUrl().contains("https://www.konga.com/"))
            //Pass
            System.out.println("Correct Webpage");
        else
            //Fail
            System.out.println("Wrong Webpage");
        Thread.sleep(5000);

        //Maximize the browser
        driver.manage().window().maximize();

        //Click on the Signin/Login button on the homepage

        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/nav/div/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 1)
    public void Signin() throws InterruptedException {
        //2.  Signin to Konga successfully

        // Input username on the username field
        driver.findElement(By.id("username")).sendKeys("kongamail@mailinator.com");


        // Locate the password field and input a valid password
        driver.findElement(By.id("password")).sendKeys("123456");

        //Click on the login button
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void Computers() throws InterruptedException {
        //3. Click Computers and accessories from Categories
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(5000);
        System.out.println("Done Mouse hover on Computer and accessories from Menu");

        //4. Click Laptop Subcategory
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[2]")).click();
        Thread.sleep(10000);
        System.out.println("Selected macbooks from Menu");

        //5. Click on Apple Macbooks

        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[4]/a/ul/li[1]/a")).click();
        System.out.println("Apple macbook selected");
        Thread.sleep(5000);



        // 6. Add an Item to the cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
        System.out.println("Item added to cart");
        Thread.sleep(5000);


        // 7. Continue to check out and Select Address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[1]/div/div[1]/div/a[2]")).click();
        System.out.println("Address selected");
        Thread.sleep(15000);


        //8. Continue to make Payment
            //Click the pay now option
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        System.out.println("Pay now option selected");
        Thread.sleep(5000);


            //Click on continue to Payment
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        System.out.println("Continue to Payment selected");
        Thread.sleep(20000);



    }
   @Test (priority = 3)
    public void CardDetails() throws InterruptedException {
       //9. Select a Card Payment Method
       //9a Change from default to Iframe

        WebElement paymethod = driver.findElement(By.tagName("iframe"));

        driver.switchTo().frame("kpg-frame-component");
        Thread.sleep(7000);
        System.out.println("Payment Method");

       //9b Select card payment method
        WebElement cardpayment = driver.findElement(By.className("Card"));
        cardpayment.click();
        System.out.println("Card Payment method selected");
        Thread.sleep(7000);

       //10. Input Invalid Card Details
       // Input invalid card number on the card number field
       WebElement carddigit = driver.findElement(By.id("card-number"));
       carddigit.sendKeys("123456781234");
       Thread.sleep(5000);

        //Input an expiry date for the card
        WebElement datedigit = driver.findElement(By.id("expiry"));
        datedigit.sendKeys("0624");
        Thread.sleep(5000);

       //Input a CVV number
        WebElement cvv = driver.findElement(By.id("cvv"));
        cvv.sendKeys("123");
        Thread.sleep(5000);

        //11. Print Out the error message: Invalid Card Number
        WebElement error = driver.findElement(By.id("card-number_unhappy"));
        System.out.println(error.getText());
        System.out.println("Error Message Printed");
        Thread.sleep(5000);

        //12. Close the iFrame that displays the input card model
        WebElement closeframe = driver.findElement(By.className("data-card__close"));
        closeframe.click();
        Thread.sleep(5000);

        //Exit Iframe web
        driver.switchTo().defaultContent();
        Thread.sleep(5000);

    }
    @AfterTest
    public void closeBrowser() {
        //Quit the browser
        driver.quit();
    }

}

