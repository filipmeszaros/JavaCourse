package org.company;

import org.openqa.selenium.*;
import org.selenium.SeleniumConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Quite nasty piece of code that will fetch prices of hotels at Locktrip and Booking and compare its prices.
 */
public class HotelPriceFetcher extends SeleniumConfiguration {
    // Dublin, Manchester, Varsava, Krakov,
    static final String DESTINATION = "Dublin";
    static final String EMAIL = "meszaros.filip@gmail.com";
    static final String LOCKTRIP_PASSWORD = "TODO";
    static final String BOOKING_PASSWORD = "TODO";

    static TreeMap<String, Double> LockTripPrices = new TreeMap<>();
    static TreeMap<String, Double> BookingPrices = new TreeMap<>();

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
        long startTime = System.currentTimeMillis();

        lockTrip_login();
        lockTrip_findHotelInTerm();
        lockTrip_prepareResults();
        lockTrip_processAllPagesOfResults();

        booking_login();
        booking_findHotelInTerm();
        booking_prepareResults();
        booking_processAllPagesOfResults();

        both_compareAndSavePriceComparison();

        long endTime = System.currentTimeMillis();
        long secondsElapsed = ( endTime - startTime ) / 1000L;
        System.out.println("Elapsed time is " + secondsElapsed + " seconds");
    }


    public static void lockTrip_login() throws InterruptedException {
        driver.get("https://locktrip.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[contains(text(), 'Log in')]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("(//*[@name='email'])[2]")).sendKeys(EMAIL);
        driver.findElement(By.xpath("(//*[@name='password'])[2]")).sendKeys(LOCKTRIP_PASSWORD);
        driver.findElement(By.xpath("(//*[contains(text(), 'Log in')])[3]")).click();
        Thread.sleep(5000);
        boolean checkCAPTCHA = false;
    }

    public static void lockTrip_findHotelInTerm() throws InterruptedException {
        WebElement destinationInput = driver.findElement(By.xpath("(//*[contains(@class,'MuiGrid-item')])[15]"));
        destinationInput.click();
        Thread.sleep(1000);
        destinationInput.findElement(By.xpath("//input")).sendKeys(DESTINATION);
        Thread.sleep(1000);
        destinationInput.findElement(By.xpath("//input")).sendKeys(Keys.TAB);

        WebElement fromDatePicker = driver.findElement(By.xpath("//input[@placeholder='Check In']"));
        fromDatePicker.click();
        Thread.sleep(200);
        WebElement fromDate = driver.findElement(By.xpath("//td[contains(@aria-label, 'September 29')]"));
        fromDate.click();
        WebElement toDate = driver.findElement(By.xpath("//td[contains(@aria-label, 'September 30')]"));
        toDate.click();
        Thread.sleep(200);

        WebElement searchIcon = driver.findElement(By.xpath("(//button)[8]"));
        searchIcon.click();
        Thread.sleep(40000);
    }

    /**
     * Prepare results - sort according to lowest price and select maximum number of results on page
     */
    public static void lockTrip_prepareResults() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id='select-rows']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//li[@data-value='150']")).click();
        Thread.sleep(15000);
    }

    public static void lockTrip_processAllPagesOfResults() throws InterruptedException {

        System.out.println("Parsing LockTrip hotel results from first page");
        lockTrip_fetchHotelPricesFromCurrentPage();

        //Process first 100 pages until page cannot be accessed
        for (int i = 2; i <= 100; i++) {
            //Access next page
            String xpathOfNextElement = String.format("//div[text()='%s']", i);
            try {
                WebElement paginationElement = driver.findElement(By.xpath("(//div[contains(@class, 'MuiGrid-root') and contains(@class, 'MuiGrid-container') and contains(@class, 'MuiGrid-justify-xs-flex-end')])[2]"));
                WebElement nextPage = paginationElement.findElement(By.xpath(xpathOfNextElement));
                nextPage.click();
                Thread.sleep(10000);
                System.out.println("Parsing LockTrip hotel results from page number " + i);
                lockTrip_fetchHotelPricesFromCurrentPage();
            } catch (NoSuchElementException e) {
                System.out.println("We are on last page of hotel results");
                break;
            }
        }
        System.out.println("LockTrip - number of hotels in " + DESTINATION + ": " + LockTripPrices.size());
    }

    public static void lockTrip_fetchHotelPricesFromCurrentPage() throws InterruptedException {
        WebElement resultsContainer = driver.findElement(By.xpath("//div[contains(@class,'MuiGrid-container')]"));
        List<WebElement> allH6Elements = resultsContainer.findElements(By.xpath("//h6"));

        String hotelName = null;
        double hotelPrice = 0.0;

        //Skip first 16 lines
        for (int i = 17; i < allH6Elements.size(); i++) {
            if (hotelName == null) {
                hotelName = allH6Elements.get(i).getText().trim();
                continue;
            }

            if (hotelPrice == 0.0) {
                String tmp = allH6Elements.get(i).getText().replace(",","").replaceAll("[^\\d.]", "").trim();
                hotelPrice = Double.parseDouble(tmp);
            }

            LockTripPrices.put(trimHotelName(hotelName), hotelPrice);
            hotelName = null;
            hotelPrice = 0.0;
        }
    }

    public static void booking_login() throws InterruptedException {
        driver.get("https://booking.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[contains(text(), 'Sign in')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(EMAIL);
        driver.findElement(By.xpath("(//button)[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(BOOKING_PASSWORD);
        driver.findElement(By.xpath("//span[contains(text(), 'Sign in')]")).click();
        Thread.sleep(5000);

        try {
            driver.findElement(By.xpath("//button/span[contains(text(), 'Accept')]")).click();
            Thread.sleep(1000);
        } catch (NoSuchElementException e) {
            System.out.println("Cookies are already accepted!");
        }

        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Accept')]")).click();
            Thread.sleep(1000);
        } catch (NoSuchElementException|ElementNotInteractableException e) {
            System.out.println("Cookies are already accepted!");
        }
    }

    public static void booking_findHotelInTerm() throws InterruptedException {
        WebElement destinationInput = driver.findElement(By.xpath("//input[@id='ss']"));
        destinationInput.click();
        destinationInput.sendKeys(Keys.CONTROL, "A", Keys.BACK_SPACE);
        destinationInput.sendKeys(DESTINATION);
        Thread.sleep(1000);
        destinationInput.sendKeys(Keys.TAB);
        destinationInput.click();

        WebElement fromDatePicker = driver.findElement(By.xpath("//*[@data-mode='checkin']"));
        fromDatePicker.click();
        Thread.sleep(1500);
        WebElement fromDate = driver.findElement(By.xpath("//span[contains(@aria-label, '29 September 2020')]/span"));
        fromDate.click();
        WebElement toDate = driver.findElement(By.xpath("//span[contains(@aria-label, '30 September 2020')]/span"));
        toDate.click();
        Thread.sleep(500);

        WebElement searchIcon = driver.findElement(By.xpath("//span[contains(text(), 'Search')]/parent::button"));
        searchIcon.click();
        Thread.sleep(20000);
    }

    public static void booking_prepareResults() throws InterruptedException {
        driver.findElement(By.xpath("//a[@data-title='Choose your currency']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@data-currency='USD']")).click();
        Thread.sleep(10000);
    }

    public static void booking_processAllPagesOfResults() throws InterruptedException {
        boolean hasNextPage = true;
        int i = 1;

        while (hasNextPage == true) {
            System.out.println("Parsing Booking hotel results from page number " + i);
            booking_fetchHotelPricesFromCurrentPage();
            try {
                WebElement nextPageElement = driver.findElement(By.xpath("//a[@title='Next page']"));
                nextPageElement.click();
            } catch (NoSuchElementException e) {
                hasNextPage = false;
                System.out.println("We are on last page of hotel results");
            }
            i++;
            Thread.sleep(5000);
        }
        System.out.println("Booking - number of hotels in " + DESTINATION + ": " + BookingPrices.size());
    }

    public static void booking_fetchHotelPricesFromCurrentPage() {
        List<WebElement> hotelNamesElements = driver.findElements(By.xpath("//a[contains(@class,'hotel_name_link')]/span[1]"));
        List<String> hotelNames = new ArrayList<>();
        for (WebElement hotelNameElement : hotelNamesElements) {
            hotelNames.add(hotelNameElement.getText().trim());
        }

        List<WebElement> hotelPricesElements = driver.findElements(By.xpath("//div[contains(@class, 'bui-price-display__value') and contains(@class, 'prco-inline-block-maker-helper')]"));
        List<Double> hotelPrices = new ArrayList<>();

        for (WebElement hotelPriceElement : hotelPricesElements) {
            try {
                String hotelPrice = hotelPriceElement.getText().replace(",", "").trim();
                hotelPrices.add(Double.parseDouble(hotelPrice.replaceAll("[^\\d.]", "")));
            } catch (Exception e) {
                hotelPrices.add(0.0);
            }
        }

        //System.out.println("Booking hotel names size: " + hotelNames.size());
        //System.out.println("Booking hotel prices size: " + hotelPrices.size());

        for (int i = 0; i < hotelNames.size(); i++) {
            BookingPrices.put(trimHotelName(hotelNames.get(i)), hotelPrices.get(i));
        }
    }

    /**
     * Step 1: Process each line of LockTrip results and check if identical hotel is in Booking
     * If yes - save both prices and price comparison
     * If no - save only LockTrip price
     *
     * Step 2: process each line of Booking results and check if result is already saved (or if similar hotel is in LockTrip)
     * If yes - skip, hotel is already saved
     * If no - save only Booking price
     */
    public static void both_compareAndSavePriceComparison() throws FileNotFoundException, UnsupportedEncodingException {

        System.out.println("Saving price comparison results to a CSV file");
        int numberOfOverlappingHotels = 0;
        double sumOfAllDiscounts = 0.0;

        String lockTripHotelName, bookingHotelName;
        Double lockTripHotelPrice, bookingHotelPrice;
        boolean hotelFoundOnSecondSite = false;
        double priceDifferenceInPercentage;

        String CSV_FILENAME = "C:\\Users\\Filip\\Desktop\\LockTripPriceComparisons\\" + DESTINATION + "_price_comparison.csv";
        OutputStream outputFile = new FileOutputStream(CSV_FILENAME);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputFile, StandardCharsets.UTF_16));
        pw.println("Hotel name,LockTrip price [USD],Booking price [USD],Price difference compared to booking [%]");

        //Step 1: Process each line of LockTrip results and check if identical hotel is in Booking
        for (Map.Entry<String, Double> lockTripHotel : LockTripPrices.entrySet()) {
            lockTripHotelName = lockTripHotel.getKey();
            lockTripHotelPrice = lockTripHotel.getValue();

            for (Map.Entry<String, Double> bookingHotel : BookingPrices.entrySet()) {
                bookingHotelName = bookingHotel.getKey();
                bookingHotelPrice = bookingHotel.getValue();

                //LockTrip hotel is on booking - save both prices and price comparison
                if (areTheSameHotels(lockTripHotelName, bookingHotelName)) {
                    //in case that booking won't fetch hotel price -> hotel price = 0 -> price difference will be 0
                    if (bookingHotelPrice != 0) {
                        priceDifferenceInPercentage = (lockTripHotelPrice - bookingHotelPrice) / (bookingHotelPrice * 1.0) * 100.0;
                    } else {
                        priceDifferenceInPercentage = 0.0;
                    }
                    pw.println(lockTripHotelName + "," + lockTripHotelPrice + "," + bookingHotelPrice + "," + String.format("%.2f", priceDifferenceInPercentage));
                    hotelFoundOnSecondSite = true;
                    numberOfOverlappingHotels++;
                    sumOfAllDiscounts = sumOfAllDiscounts + priceDifferenceInPercentage;
                    break;
                }
            }

            //LockTrip hotel is not in booking - save only LockTrip price
            if (hotelFoundOnSecondSite == false) {
                pw.println(lockTripHotelName + "," + lockTripHotelPrice + "," + "" + "," + "");
            }

            hotelFoundOnSecondSite = false;
        } //end of: Step 1: Process each line of LockTrip results and check if identical hotel is in Booking



        //Step 2: process each line of Booking results and check if result is already saved (or if similar hotel is in LockTrip)
        for (Map.Entry<String, Double> bookingHotel : BookingPrices.entrySet()) {
            bookingHotelName = bookingHotel.getKey();
            bookingHotelPrice = bookingHotel.getValue();

            for (Map.Entry<String, Double> lockTripHotel : LockTripPrices.entrySet()) {
                lockTripHotelName = lockTripHotel.getKey();
                lockTripHotelPrice = lockTripHotel.getValue();

                //Booking hotel is on LockTrip - hotel is already saved from Step 1
                if (areTheSameHotels(bookingHotelName, lockTripHotelName)) {
                    hotelFoundOnSecondSite = true;
                    break;
                }
            }

            //Booking hotel is not in LockTrip - save only Booking price
            if (hotelFoundOnSecondSite == false) {
                pw.println(bookingHotelName + "," + "" + "," + bookingHotelPrice + "," + "");
            }

            hotelFoundOnSecondSite = false;

        }//End of: Step 2: process each line of Booking results and check if result is already saved (or if similar hotel is in LockTrip)

        pw.println("STATISTICS,,,," +
                "Number of LockTrip hotels: " + LockTripPrices.size() + "," +
                "Number of Booking hotels: " + BookingPrices.size() + "," +
                "Number of overlapping hotels: " + numberOfOverlappingHotels + "," +
                "Average price difference: " + String.format("%.2f", sumOfAllDiscounts / (numberOfOverlappingHotels * 1.0)));
        pw.close();
    }

    /**
     * Returns true if hotels are with the same name
     * @param firstHotel
     * @param secondHotel
     * @return
     */
    public static boolean areTheSameHotels(String firstHotel, String secondHotel) {
        String updatedfirstHotel = firstHotel.trim().replaceAll("[^A-Za-z0-9]", "");
        String updatedSecondHotel = secondHotel.trim().replaceAll("[^A-Za-z0-9]", "");

        //Compare hotels with ignore case
        return updatedfirstHotel.equalsIgnoreCase(updatedSecondHotel);
    }

    /**
     * Replace newlines and commas from hotel name
     * @param hotelName
     * @return
     */
    public static String trimHotelName(String hotelName) {
        return hotelName.replaceAll("[\n\r]", "").replace(",","").trim();
    }
}
