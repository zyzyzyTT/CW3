import java.time.LocalDate;
import java.util.Random;

public class StockGenerator {
	//generate randomly
	public Stock generateStock() {
		// get a random  ticker name
		String randomTickerName = this.getRandomString(4);
		// get a random date
		LocalDate now = LocalDate.now();
		//long nowVal = now.toEpochDay();
		long dateRand = (long)(Math.random() * now.toEpochDay());
		LocalDate outDate = LocalDate.ofEpochDay(dateRand);
		// get a random number
		Random random = new Random();
		double openRandom = random.nextDouble();
		double highRandom = random.nextDouble();
		double lowRandom = random.nextDouble();
		double closeRandom = random.nextDouble();
		double volumeRandom = random.nextDouble();
		
		Stock stockRandom = new Stock(randomTickerName,outDate,openRandom,highRandom,lowRandom,closeRandom,volumeRandom);
		return stockRandom;
	}
	
	// get a random string 
	 public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(52);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }

}
