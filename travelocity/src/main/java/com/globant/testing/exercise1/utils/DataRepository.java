package com.globant.testing.exercise1.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRepository {

	public List<String> getSortFlightsOptions() {
		return Arrays.asList("Price (Lowest)","Price (Highest)", "Duration (Shortest)", "Duration (Longest)", "Departure (Earliest)","Departure (Latest)","Arrival (Earliest)","Arrival (Latest)");
	}

	public Map<String, String> getBasicFormInfo() {
		Map<String, String> basicInfo  = new HashMap<String, String>() {{
		    put("from", "LAS");
		    put("to", "LAX");
		    put("departureDate", "2020-01-03");
		    put("arriveDate", "2020-05-03");
		    put("adults", "1");
		}};
		
		return basicInfo;
	}

}
