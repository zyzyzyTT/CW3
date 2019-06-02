import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;

public class Main {
	// the name of output file
	public static String outputFileName = "output.txt";
	// the name of the file
	public static final String dataFileName = "priceDataCW3.csv";
	//public static final String dataFileName = "prices20lines.csv";
	// the list of stock names
	public static ArrayList<String> TickersName = new ArrayList<>();
	// the scanner
	static Scanner kb = new Scanner(System.in);
	// the year users enter
	public static int yearChoose;
	// the choice of data points
	public static int choiceofDataPoints;
	// the choice of time 
	public static int choiceOfTime;
	// the earliest year
	public static int startYear;
	// the latest year
	public static int endYear;
	// the month of the year user enter
	public static YearMonth monthChoose;
	// the period user enter
	public static DatePeriod periodChoose;
	
	
	public static void main(String[] args) {
		FileUtils.createFile(outputFileName);
		List<Stock> stock = FileUtils.readFile(dataFileName);
		// sort the list
		Collections.sort(stock);
		System.out.println("The following tickers are available: ");
		showTickersList(stock);
		System.out.println("The valid time period is from");
	    showTimePeriod(stock);
	    String[] TickersChoose = getTicker();
	    showMenuOfTime();
	    showMenuOfDataPoints(stock,monthChoose,yearChoose,periodChoose,TickersChoose,choiceOfTime);
	    
	}
	
	// show tickers in the one month
	public static DoubleSummaryStatistics showTickersForMonth(List<Stock> stocks, YearMonth ym, String[] tickersChoose, int choiceofdatapoints) {
		DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
		// handle with closing price
		if (choiceofdatapoints == 1) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (YearMonth.from(s.getDate()).equals(ym) && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Closing price: "+s.getClose());
						FileUtils.writeFile(outputFileName, "Ticker is "+s.getTicker()+" "+s.getDate()+" Closing price: "+s.getClose());
						stats.accept(s.getClose());
					}
				}
			}
		}
		// handle with spread
		if (choiceofdatapoints == 2) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (YearMonth.from(s.getDate()).equals(ym) && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Spread: "+s.getSpread());
						FileUtils.writeFile(outputFileName, "Ticker is "+s.getTicker()+" "+s.getDate()+" Spread: "+s.getSpread());
						stats.accept(s.getSpread());
					}
				}
			}
		}
		// handle with volume
		if (choiceofdatapoints == 3) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (YearMonth.from(s.getDate()).equals(ym) && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Volume: "+s.getVolume());
						FileUtils.writeFile(outputFileName, "Ticker is "+s.getTicker()+" "+s.getDate()+" Volume: "+s.getVolume());
						stats.accept(s.getVolume());
					}
				}
			}
		}
		return stats;
	}
	
	// show tickers in the one year
	public static DoubleSummaryStatistics showTickersForYear(List<Stock> stocks, int year, String[] tickersChoose, int choiceofdatapoints) {
		DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
		// handle with closing price
		if (choiceofdatapoints == 1) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (s.getDate().getYear() == year && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Closing price: "+s.getClose());
						FileUtils.writeFile(outputFileName, "Ticker is "+s.getTicker()+" "+s.getDate()+" Closing price: "+s.getClose());
						stats.accept(s.getClose());
					}
				}
			}
		}
		// handle with spread
		if (choiceofdatapoints == 2) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (s.getDate().getYear() == year && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Spread: "+s.getSpread());
						FileUtils.writeFile(outputFileName,"Ticker is "+s.getTicker()+" "+s.getDate()+" Spread: "+s.getSpread());
						stats.accept(s.getSpread());
					}
				}
			}
		}
		// handle with volume
		if (choiceofdatapoints == 3) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (s.getDate().getYear() == year && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Volume: "+s.getVolume());
						FileUtils.writeFile(outputFileName, "Ticker is "+s.getTicker()+" "+s.getDate()+" Volume: "+s.getVolume());
						stats.accept(s.getVolume());
					}
				}
			}
		}
		return stats;
	}
	
	//show tickers in a period of time 
	public static DoubleSummaryStatistics showTickersInPeriodOfTime(List<Stock> stocks, DatePeriod dp, String[] tickersChoose, int choiceofdatapoints) {
		DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
		// handle with closing price
		if (choiceofdatapoints == 1) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (dp.isInPeriod(s.getDate()) && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Closing price: "+s.getClose());
						FileUtils.writeFile(outputFileName, "Ticker is "+s.getTicker()+" "+s.getDate()+" Closing price: "+s.getClose());
						stats.accept(s.getClose());
					}
				}
			}
		}
		// handle with spread
		if (choiceofdatapoints == 2) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (dp.isInPeriod(s.getDate()) && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Spread: "+s.getSpread());
						FileUtils.writeFile(outputFileName,"Ticker is "+s.getTicker()+" "+s.getDate()+" Spread: "+s.getSpread());
						stats.accept(s.getSpread());
					}
				}
			}
		}
		// handle with volume
		if (choiceofdatapoints == 3) {
			for (int i =0; i<tickersChoose.length;++i) {
				for (Stock s : stocks) {
					if (dp.isInPeriod(s.getDate()) && s.getTicker().equals(tickersChoose[i])) {
						// print something the user chooses
						System.out.println("Ticker is "+s.getTicker()+" "+s.getDate()+" Volume: "+s.getVolume());
						FileUtils.writeFile(outputFileName, "Ticker is "+s.getTicker()+" "+s.getDate()+" Volume: "+s.getVolume());
						stats.accept(s.getVolume());
					}
				}
			}
		}
		return stats;
	}
	
	
	//get all names of Tickers 
	public static void showTickersList(List<Stock> stocks) {
		ArrayList<String> tickers = new ArrayList<>();
		String ticker;
		for (Stock s : stocks) {
			ticker = s.getTicker();
			if (!(tickers.contains(ticker))) {
				tickers.add(ticker);
			}
			if (!(TickersName.contains(ticker))) {
				TickersName.add(ticker);
			}
		}
		System.out.println(tickers);
	}
	
	//show the valid time period 
	public static void showTimePeriod(List<Stock> stocks) {
		int numberoflist = stocks.size();
		LocalDate start = stocks.get(0).getDate();
		startYear = start.getYear();
		LocalDate end = stocks.get(numberoflist-1).getDate();
		endYear = end.getYear();
		System.out.println(start+" to "+end);
	}
	
	// get right ticker users choose
	public static String[] getTicker() {
		System.out.println("Please input the stock ticker(eg AAPL), spliting with comma with at most 5 stocks. ");
		String[] TickersChoose = kb.nextLine().split(",");
		// handle with the problem of out of range 
		if (TickersChoose.length > 5) {
			System.out.println("Out of number range. Please enter again");
			TickersChoose = getTicker();
		}
		// check the input ticker is valid
		boolean temp = false;
		for (int i =0; i<TickersChoose.length;++i) {
			if(!(TickersName.contains(TickersChoose[i]))) {
				temp = true;
			}
		}
		if(temp) {
			System.out.println("Out of ticker range. Please enter again.");
			TickersChoose = getTicker();	
		}
		return TickersChoose;					
	}
	
	// get a right choice(1<=input<=3)
	@SuppressWarnings("finally")
	private static int getIntInput() {
		int input = 0;
		try {
			input = Integer.parseInt(kb.nextLine());
			if (input<1 || input >3) {
				System.out.println("Out of range, please enter again.");
				input = getIntInput();
				}
		}
		catch(NumberFormatException e) {
			System.out.println("That is not an int;"+"please try again");
			input = getIntInput();
		}
		finally {
			return input;
		}
	}
	
	// get a right choice
	@SuppressWarnings("finally")
	private static int getInt() {
		int input = 0;
		try {
			input = Integer.parseInt(kb.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("That is not an int;"+"please try again");
			input = getIntInput();
		}
		finally {
			return input;
		}
	}
	
	// show the Menu of data points
	public static void showMenuOfDataPoints(List<Stock> stocks,YearMonth ym, int year, DatePeriod pd, String[] tickersChoose, int choiceoftime) {
	    System.out.println("Please input a number 1-3");
	    System.out.println("1.Closing price ");
	    System.out.println("2.Spread");
	    System.out.println("3.Volume");
	    System.out.println("Please choose data points: ");
	    int choice = getIntInput();
	    choiceofDataPoints = choice;
	    if(choice == 1) {
	    	if (choiceoftime == 1) {
	    		DoubleSummaryStatistics statsformonth = showTickersForMonth(stocks, ym, tickersChoose, 1);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsformonth.getAverage());
		    	System.out.println("Closing Price maximum is "+statsformonth.getMax());
		    	System.out.println("Closing Price minimum is "+statsformonth.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsformonth.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsformonth.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsformonth.getMin());
	    	}
	    	if (choiceoftime == 2) {
	    		DoubleSummaryStatistics statsforyear = showTickersForYear(stocks, year, tickersChoose, 1);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsforyear.getAverage());
		    	System.out.println("Closing Price maximum is "+statsforyear.getMax());
		    	System.out.println("Closing Price minimum is "+statsforyear.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsforyear.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsforyear.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsforyear.getMin());
	    	}
	    	if (choiceoftime == 3) {
	    		DoubleSummaryStatistics statsforperiodtime = showTickersInPeriodOfTime(stocks, pd, tickersChoose, 1);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsforperiodtime.getAverage());
		    	System.out.println("Closing Price maximum is "+statsforperiodtime.getMax());
		    	System.out.println("Closing Price minimum is "+statsforperiodtime.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsforperiodtime.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsforperiodtime.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsforperiodtime.getMin());
	    	}
	    }
	    if(choice == 2) {
	    	if (choiceoftime == 1) {
	    		DoubleSummaryStatistics statsformonth = showTickersForMonth(stocks, ym, tickersChoose, 2);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsformonth.getAverage());
		    	System.out.println("Closing Price maximum is "+statsformonth.getMax());
		    	System.out.println("Closing Price minimum is "+statsformonth.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsformonth.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsformonth.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsformonth.getMin());
	    	}
	    	if (choiceoftime == 2) {
	    		DoubleSummaryStatistics statsforyear = showTickersForYear(stocks, year, tickersChoose, 2);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsforyear.getAverage());
		    	System.out.println("Closing Price maximum is "+statsforyear.getMax());
		    	System.out.println("Closing Price minimum is "+statsforyear.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsforyear.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsforyear.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsforyear.getMin());
	    	}
	    	if (choiceoftime == 3) {
	    		DoubleSummaryStatistics statsforperiodtime = showTickersInPeriodOfTime(stocks, pd, tickersChoose, 2);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsforperiodtime.getAverage());
		    	System.out.println("Closing Price maximum is "+statsforperiodtime.getMax());
		    	System.out.println("Closing Price minimum is "+statsforperiodtime.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsforperiodtime.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsforperiodtime.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsforperiodtime.getMin());
	    	}
	    }
	    if(choice == 3) {
	    	if (choiceoftime == 1) {
	    		DoubleSummaryStatistics statsformonth = showTickersForMonth(stocks, ym, tickersChoose, 3);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsformonth.getAverage());
		    	System.out.println("Closing Price maximum is "+statsformonth.getMax());
		    	System.out.println("Closing Price minimum is "+statsformonth.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsformonth.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsformonth.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsformonth.getMin());
	    	}
	    	if (choiceoftime == 2) {
	    		DoubleSummaryStatistics statsforyear = showTickersForYear(stocks, year, tickersChoose, 3);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsforyear.getAverage());
		    	System.out.println("Closing Price maximum is "+statsforyear.getMax());
		    	System.out.println("Closing Price minimum is "+statsforyear.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsforyear.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsforyear.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsforyear.getMin());
	    	}
	    	if (choiceoftime == 3) {
	    		DoubleSummaryStatistics statsforperiodtime = showTickersInPeriodOfTime(stocks, pd, tickersChoose, 3);
		    	System.out.println("-------------------------------");
		    	System.out.println("Closing Price Average is "+statsforperiodtime.getAverage());
		    	System.out.println("Closing Price maximum is "+statsforperiodtime.getMax());
		    	System.out.println("Closing Price minimum is "+statsforperiodtime.getMin());
		    	FileUtils.writeFile(outputFileName, "-------------------------------");
		    	FileUtils.writeFile(outputFileName, "Closing Price Average is "+statsforperiodtime.getAverage());
		    	FileUtils.writeFile(outputFileName, "Closing Price maximum is "+statsforperiodtime.getMax());
		    	FileUtils.writeFile(outputFileName, "Closing Price minimum is "+statsforperiodtime.getMin());
	    	}
	    }
	    //showMenuOfDataPoints();
	}
	
	// show the Menu of time choose
	public static void showMenuOfTime() {
	    System.out.println("Please input a number 1-3 to the time constrain you want");
	    System.out.println("1.Month of year");
	    System.out.println("2.Year");
	    System.out.println("3.Arbitrary");
	    System.out.println("Please choose the time constrain: ");
	    int choice = getIntInput();
	    choiceOfTime = choice;
	    if(choice == 1) {
	    	monthChoose = showMonth();
	    }
	    if(choice == 2) {
	    	yearChoose = showYear();
	    }
	    if(choice == 3) {
	    	periodChoose = showArbitrary();
	    }
	    //showMenuOfTime();
	}
	
	// handle with input of year
	public static int showYear() {
		System.out.println("Please enter a year which is valid.");
		int year = getInt();
		if (year >= startYear && year <= endYear) {
			return year;
		}
		year = showYear();
		return year;
	}
	
	// handle with input of YearMonth
	public static YearMonth showMonth() {
		System.out.println("Please enter a month of a year which is valid, using format mm/yyyy.");
		String[] input = kb.nextLine().split("/");
		int inputMonth = Integer.parseInt(input[0]);
		int inputYear = Integer.parseInt(input[1]);
		YearMonth ym = YearMonth.of(inputYear, inputMonth);
		return ym;
	}
	
	// handle the input of a period of time 
	public static DatePeriod showArbitrary() {
		// get the beginning date
		System.out.println("Please first enter the beginning date which is valid, using format dd/mm/yyyy.");
		String[] input = kb.nextLine().split("/");
		int inputDay = Integer.parseInt(input[0]);
		int inputMonth = Integer.parseInt(input[1]);
		int inputYear = Integer.parseInt(input[2]);
		LocalDate start = LocalDate.of(inputYear, inputMonth, inputDay);
		// get the end date
		System.out.println("Please first enter the end date which is valid, using format dd/mm/yyyy.");
		String[] inputend = kb.nextLine().split("/");
		int inputDayend = Integer.parseInt(inputend[0]);
		int inputMonthend = Integer.parseInt(inputend[1]);
		int inputYearend = Integer.parseInt(inputend[2]);
		LocalDate end = LocalDate.of(inputYearend, inputMonthend, inputDayend);
		DatePeriod dp = new DatePeriod(start,end);
		return dp;
	}
	
}
