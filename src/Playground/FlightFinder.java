package Playground;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class FlightFinder {

	private Map<String, List<Flight>> allFlights = new HashMap<>();

	public FlightFinder(Map<String, List<Flight>> allFlights) {
		this.allFlights = allFlights;
	}

	public List<NavigableSet<Flight>> findFlights(int dayOfMonth, int month, int year, 
	        int preferredDepartureStartHour, int preferredDepartureEndHour, 
	        String departureCity, String arrivalCity, String finalArrivalCity,
			String departureCityTimeZone, String arrivalCityTimeZone) {
		
		List<NavigableSet<Flight>> result = new ArrayList<>();
        
        // Step 1: Construct ZonedDateTime objects to represent User-specified time interval when flights depart

                   LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
                   LocalTime localTimeDepartureStart = LocalTime.of(preferredDepartureStartHour, 00);
                   LocalTime localTimeDepartureEnd = LocalTime.of(preferredDepartureEndHour, 00);
                   ZonedDateTime zonedDepartureDateTimeStart = ZonedDateTime.of(localDate, localTimeDepartureStart, ZoneId.of(departureCityTimeZone));
                   ZonedDateTime zonedDepartureDateTimeEnd = ZonedDateTime.of(localDate, localTimeDepartureEnd, ZoneId.of(departureCityTimeZone));
                   // System.out.println(zonedDepartureDateTimeStart);
                   // System.out.println(zonedDepartureDateTimeEnd);

        // Step 2: Find departing flights at departureCity
        List<Flight> allDepartingFlights = allFlights.get(departureCity);
        
        NavigableSet<Flight> departingflights = new TreeSet<>();
        
                   // Your code
                   // Tip: Methods like isAfter can be used to find flights in the specified user time interval
        			for (Flight flight : allDepartingFlights) {
        				if (flight.getDepartureTime().isAfter(LocalDateTime.of(localDate, localTimeDepartureStart).minusSeconds(1)) && (flight.getDepartureTime().isBefore(LocalDateTime.of(localDate, localTimeDepartureEnd)))) {
        					if (flight.getArrivalCity() == arrivalCity) {
            					departingflights.add(flight);
        					}
        				}
        			}                
        
        // Step 3: Find connecting flights
        //   Constraint 1: Departing at arrivalCity (e.g., Dubai) and arrive at finalArrivalCity (e.g., Mumbai)
        //   Constraint 2: Should start at least two hours after the arrival time of the first flight in the above navigable set

        List<Flight> allConnectingFlights = allFlights.get(arrivalCity);        
        
        NavigableSet<Flight> connectingflights = new TreeSet<>();
        
        
        			LocalDateTime earliestConnectingFlightTime = departingflights.first().getArrivalTime().plusHours(2).minusSeconds(1);
                    for (Flight flight : allConnectingFlights) {
                    	if (finalArrivalCity == flight.getArrivalCity() && flight.getDepartureTime().isAfter(earliestConnectingFlightTime)) {
                    		connectingflights.add(flight);
                    	}
                    }
        
        result.add(departingflights);
        result.add(connectingflights);
        
        return result;
	}

}
