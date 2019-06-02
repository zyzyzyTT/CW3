import java.time.LocalDate;

public class Stock implements Comparable<Stock>{
	private String Ticker;
	private LocalDate Date;
	private double Open;
	private double High;
	private double Low;
	private double Close;
	private double Volume;
	
	//constructor
	public Stock(String ticker, LocalDate date, double open, double high, double low, double close, double volume) {
		this.Ticker = ticker;
		this.Date = date;
		this.Open = open;
		this.High = high;
		this.Low = low;
		this.Close = close;
		this.Volume = volume;
	}
	
	// constructor by line
	public Stock(String line) {
		if (line == null) {
			throw new NullPointerException("null String passed to Stock constructor");
		}
		String[] data = line.split(",");
		if (data.length != 7) {
			throw new IllegalArgumentException("Bad data read from file");
		}
		this.Ticker = data[0];
		// validate the input of date
		String[] date = data[1].split("/");
		if (date.length != 3) {
			throw new IllegalArgumentException("Bad data read from date");
		}
		int day = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);
		this.Date = LocalDate.of(year,month,day);
				
		this.Open = Double.parseDouble(data[2]);
		this.High = Double.parseDouble(data[3]);
		this.Low = Double.parseDouble(data[4]);
		this.Close = Double.parseDouble(data[5]);
		this.Volume = Double.parseDouble(data[6]);
	}
	
	// get functions
	public String getTicker() {
		return Ticker;
	}
	
	public LocalDate getDate() {
		return Date;
	}
	
	public double getOpen() {
		return Open;
	}
	
	public double getHigh() {
		return High;
	}
	
	public double getLow() {
		return Low;
	}
	
	public double getClose() {
		return Close;
	}
	
	public double getVolume() {
		return Volume;
	}
	
	public double getSpread() {
		return (this.High-this.Low);
	}
	
	// equal function
	public boolean equals(Stock other) {
		return true;
	}
	
	// compare function
	public int compareTo(Stock toCompare) {
		LocalDate date = this.getDate();
		LocalDate dateToCompare = toCompare.getDate();
		if(date.isAfter(dateToCompare)) {
			return 1;
		}
		else if(date.isBefore(dateToCompare)) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	// override toString function 
	@Override
	public String toString() {
		return "Stock [Ticker=" + Ticker + ", Date=" + Date + ", Open=" + Open + ", High=" + High +", Low=" + Low + ", Close=" + Close + ",Volume=" + Volume + "]";
	}

}
