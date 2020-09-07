package com.linhhd.rest.webservice.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean somebean = new SomeBean("value1", "value2", "value3");
		String[] strs = {"field1","field2"};
		return filter(somebean, strs);
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> listSomeBean = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value11", "value12", "value13"));
		String[] strs = {"field2","field3"};
		return filter(listSomeBean, strs);
	}
	
	public MappingJacksonValue filter(Object value, String[] fields) {
		Set<String> properties = new HashSet<String>(Arrays.asList(fields));
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(properties );
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(value);
		mapping.setFilters(filters);
		return mapping;
	}
}
