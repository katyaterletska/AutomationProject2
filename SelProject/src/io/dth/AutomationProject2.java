package io.dth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AutomationProject2 {

	public static void main(String[] args) {
		
		
				
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//1. Go to http://carfax.com
		driver.get("http://carfax.com");
		
		//2. Click on Find a Used Car
		WebElement FindAUsedCar = driver.findElement(By.linkText("Find a Used Car"));
		FindAUsedCar.click();
		smartWait(1000);
	
		//3. Verify the page title contains the word “Used Cars”
		String actualTitle = driver.getTitle();
		String expectedTitle = "Used Cars";
		
		if (actualTitle.contains(expectedTitle)) {
			System.out.println("Task3: PASS");
		}else {
			System.out.println("Task3: FAIL");
		}
		
		//4. Choose “Tesla” for  Make.
		 WebElement tesla = driver.findElement(By.xpath("//select[@name='make']"));
	     Select model1 = new Select(tesla);
	     
	     model1.selectByValue("Tesla");
	     smartWait(2000);
	    
	     
		//5. Verify that the Select Model dropdown box contains 3 current Tesla models - (Model 3, Model S, Model X). 
		//6.Choose “Model S” for Model.
		WebElement model = driver.findElement(By.xpath("//select[@name='model']"));
		Select selectModel = new Select(model);
		selectModel.selectByValue("Model S");
		smartWait(1000);
		
		List<WebElement> options = selectModel.getOptions();
		//System.out.println(options.size());
		List<String> actualMList = new ArrayList<>();
		smartWait(1000);
		
		for (WebElement AML: options) {
			actualMList.add(AML.getText().trim());
		}
		//System.out.println(actualMList);
		if(actualMList.contains("Model 3") && actualMList.contains("Model S") && actualMList.contains("Model X")) {
			System.out.println("Task5: PASS");
			}else {
			System.out.println("Task5: FAIL");
			}
		smartWait(1000);
	
	    
	    //7. Enter the zipcode as 22182 and click Next
	    WebElement zip = driver.findElement(By.name("zip"));
	    zip.sendKeys("22182");
	    smartWait(1000);
	
	    driver.findElement(By.id("make-model-form-submit")).click();
	    
	    
	    //8. Verify that the page has “Step 2 – Show me cars with” text
		String step2 = "Step 2 - Show me cars with";
		
		if (driver.getPageSource().contains(step2)) {
			System.out.println("Task8: PASS");
		}else {
			System.out.println("Task8: FAIL");
		}
		
		//9. Click on all 4 checkboxes.
		List<WebElement> checkbox =  driver.findElements(By.className("checkbox-list-item--fancyCbx"));
		for(WebElement i : checkbox) {
			if(!i.isSelected()) {
				i.click();
			}
		}
		smartWait(1000);
		
		//10. Save the result of “Show me X Results” button to a variable. In this case it is 7.
		String actualXResult = driver.findElement(By.className("totalRecordsText")).getText();
		//System.out.println(actualXResult);
		
		//11. Click on “Show me x Results” button. 
		driver.findElement(By.xpath("//button[@class='button large primary-green']")).click();
        smartWait(1000);
        
        
        //12. On the next page, verify that the results page has the same number of results 
        //    as indicated in Step 10 by extracting the number and comparing the result
        
        //13. Verify the results also by getting the actual number of results displayed in 
        //    the page with the number in the Step 10. For this step get the count the number 
        //    of WebElements related to each result.
        String expectedXResult = driver.findElement(By.id("totalResultCount")).getText();
        //System.out.println(expectedXResult);
        if(expectedXResult.equals(actualXResult)) {
        	System.out.println("Task12: PASS");
        }else {
        	System.out.println("Task12: FAIL");
        }
        
        //14. Verify that each result contains String “Tesla Model S”.
        String expectedTMS = "Tesla Model S";
        List<WebElement> actualTMS = driver.findElements(By.className("srp-list-item-basic-info-model"));
        //System.out.println(actualTMS.size());
        int count = 0;
        
        for (WebElement i : actualTMS) {
        	if(i.getText().contains(expectedTMS)) {
        		count = count+1;
        	}
        }
        //System.out.println(count);
        //System.out.println(actualXResult);
        if ((String.valueOf(count)).equals(actualXResult)) {
        	System.out.println("Task14: PASS");
        }else {
        	System.out.println("Task14: FAIL");
        }
        
        //15. Get the price of each result and save them into a list in the order of their appearance.
        List<WebElement> price = driver.findElements(By.className("srp-list-item-price"));
        List<String> priceList = new ArrayList<>();
	
		for (WebElement i: price) {
			priceList.add(i.getText().trim());
		}
		//System.out.println(priceList);
		
		//16. Choose “Price - High to Low” option from Sort menu
		WebElement menu = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
		smartWait(1000);
		Select highToLow = new Select(menu);
		highToLow.selectByValue("PRICE_DESC");
		smartWait(1000);
		
		//17. Verify that the results are displayed from high to low price. 
		List<WebElement> sortPrice = driver.findElements(By.className("srp-list-item-price"));
        List<String> priceList1 = new ArrayList<>();
	
		for (WebElement i: sortPrice) {
			priceList1.add(i.getText().trim());
		}
		//System.out.println(priceList1);
		Collections.sort(priceList, Collections.reverseOrder());
        //System.out.println(priceList);

        if(priceList.equals(priceList1)) {
        	System.out.println("Task17: PASS");
        }else{
        	System.out.println("Task17: FAIL");
        }
        
        //18. Choose “Mileage - Low to High” option from Sort menu
        WebElement mileage = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
		smartWait(1000);
		Select lowToHigh = new Select(mileage);
		lowToHigh.selectByValue("MILEAGE_ASC");
		smartWait(1000);
		
		//19. Verify that the results are displayed from low to high mileage. 
		WebElement sortMenu1 = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
        smartWait(1000);
        Select fromMenu1 = new Select(sortMenu1);
        fromMenu1.selectByValue("MILEAGE_ASC");
        smartWait(1000);

        List<WebElement> spans = driver.findElements(By.xpath("//span"));
        List<Integer>mileageFromSpans = new ArrayList<>();
        for(int q=0;q<spans.size();q++) {
            if(spans.get(q).getText().contains("Mileage")) {
                mileageFromSpans.add(Integer.parseInt(spans.get(q).getText().substring(9, 15).replace(",", "") )   );
            }
        }
        //System.out.println(mileageFromSpans);

        for(int c=0;c<mileageFromSpans.size()-1;c++) {
            if(mileageFromSpans.get(c)<mileageFromSpans.get(c+1)) {continue;}
            else {System.out.println("Task19: FAIL");}
        }
        System.out.println("Task19: PASS");
        
        
        //20. Choose “Year - New to Old” option from Sort menu
        WebElement year = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
		smartWait(1000);
		Select newToOld = new Select(year);
		newToOld.selectByValue("YEAR_DESC");
		smartWait(1000);
		
		//21. Verify that the results are displayed from new to old year.
		List<WebElement> yearSort = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
        List<Integer> years = new ArrayList<>();
        for(WebElement w:yearSort) {
            years.add(  Integer.parseInt(w.getText().substring(0, 4)) );
        }
        //System.out.println(years);
        smartWait(1000);

        for(int b=0;b<years.size()-1;b++) {
            if(years.get(b)>=years.get(b+1)) {continue;}
            else {System.out.println("Task21: FAIL");}
        }
        System.out.println("Task21: PASS");

        driver.quit();
	
	
	}
		public static void smartWait(int a){
	        try{
	            Thread.sleep(a);
	        }catch(Exception e){

	        }
	}

}
