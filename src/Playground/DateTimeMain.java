package Playground;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;

public class DateTimeMain {
	
	// test
	
	/*public static Instant instant = Instant.now();
	
	public static int localTime = LocalTime.now().getMinute();
	public static ZonedDateTime localTimeZoned = ZonedDateTime.now(); */

	
	public static void main(String[] args) {

		/*System.out.println(instant);
		System.out.println(localTime);
		System.out.println(localTimeZoned); */
		
	    	Flight f1 = new Flight(1, "1", "Emirates", "New York", "Dubai",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
			Flight f2 = new Flight(2, "2", "Delta", "San Francisco", "Paris",
					LocalDateTime.of(2017, 12, 20, 9, 30), LocalDateTime.of(2017, 12, 20, 9, 0));
			Flight f3 = new Flight(3, "3", "Delta", "San Francisco", "Dubai",
					LocalDateTime.of(2017, 12, 20, 9, 30), LocalDateTime.of(2017, 12, 20, 10, 0));
			Flight f4 = new Flight(4, "4", "Emirates", "San Francisco", "Hong Kong",
					LocalDateTime.of(2017, 12, 20, 10, 0), LocalDateTime.of(2017, 12, 20, 10, 0));
			Flight f5 = new Flight(5, "5", "Emirates", "Dubai", "Mumbai",
					LocalDateTime.of(2017, 12, 20, 14, 0), LocalDateTime.of(2017, 12, 20, 14, 0));
			
			Map<String, List<Flight>> allFlights = new HashMap<>();
			
			allFlights.put("New York", Arrays.asList(f1));
			allFlights.put("San Francisco", Arrays.asList(f2, f3, f4));
			allFlights.put("Dubai", Arrays.asList(f5));
			
			List<NavigableSet<Flight>> result = new FlightFinder(allFlights).findFlights(20, 12, 2017, 9, 13, "San Francisco", "Dubai", "Mumbai", "America/Los_Angeles", "Asia/Dubai");

			System.out.println(result.toString());

	}

}
