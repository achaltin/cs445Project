package datastructs;

public class Date {
	int day;
	int month;
	int year;
	public Date(int m, int d, int y) {
		day = d;
		month = m;
		year = y;
	}
	public boolean equals(Date other) {
		return this.compareTo(other) == 0;
	}
	public boolean before(Date later) {
		return year < later.year
				|| ( year==later.year && month < later.month)
				|| ( year==later.year && month==later.month && day<later.day);
	}
	public boolean after(Date sooner) {
		return year > sooner.year
				|| ( year==sooner.year && month > sooner.month)
				|| ( year==sooner.year && month==sooner.month && day>sooner.day);
	}
	public String toString() {
		return month+"/"+day+"/"+year;
	}
	public int compareTo(Date other) {
		if(this.before(other))
			return -1;
		else if (this.after(other))
			return 1;
		else
			return 0;
	}
}
