import java.time.LocalDate;

public class DatePeriod {
	private LocalDate Start;
	private LocalDate End;
	
	// constructor
	public DatePeriod(LocalDate start, LocalDate end) {
		this.Start = start;
		this.End = end;
	}
	
	// get function
	public LocalDate getStart() {
		return this.Start;
	}
	
	public LocalDate getEnd() {
		return this.End;
	}
	
	// check a date whether is in the period
	public boolean isInPeriod(LocalDate date) {
		if (date.isEqual(this.getStart())||date.isEqual(this.getEnd())) {
			return true;
		}
		else if (date.isAfter(this.getStart()) && date.isBefore(this.getEnd())) {
			return true;
		}
		else {
			return false;
		}
	}

}
