package com.i2g.rms.util.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2g.rms.util.model.FilterRO;
import com.i2g.rms.util.model.FilterRO.Operator;
import com.i2g.rms.util.model.PageRO;
import com.i2g.rms.util.model.SearchRO;
import com.i2g.rms.util.model.SortRO;
import com.i2g.rms.util.model.SortRO.Order;

public class JavaToJSON {

	private void run() {

		ObjectMapper mapper = new ObjectMapper();
		SearchRO searchRO = createSearchRO();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("C:\\Karthik\\Temp\\java\\SearchRO.json"), searchRO);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(searchRO);
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(searchRO);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SearchRO createSearchRO() {
		SearchRO searchRO = new SearchRO();

		searchRO.setPaging(getPageRO());

		List<FilterRO> filterROs = new ArrayList<>();
		filterROs.add(getFilterRO(1, "EQ"));
		filterROs.add(getFilterRO(2, "NEQ"));
		filterROs.add(getFilterRO(3, "STARTS_WITH"));
		searchRO.setFilters(filterROs);

		List<SortRO> sortROs = new ArrayList<>();
		sortROs.add(getSortRO(1, "ASC"));
		sortROs.add(getSortRO(2, "DESC"));
		sortROs.add(getSortRO(3, "NONE"));
		searchRO.setSorts(sortROs);
		
		return searchRO;
	}

	private PageRO getPageRO() {
		PageRO pageRO = new PageRO();
		pageRO.setCurrentPage(1);
		pageRO.setPageSize(10);
		return pageRO;
	}

	public FilterRO getFilterRO(final int index, final String operator) {
		FilterRO filterRO = new FilterRO();

		filterRO.setField("filterField " + index);

		if (operator != null && !operator.trim().isEmpty()) {
			if (operator.equalsIgnoreCase("EQ")) {
				filterRO.setOperator(Operator.EQ);
			} else if (operator.equalsIgnoreCase("NEQ")) {
				filterRO.setOperator(Operator.NEQ);
			} else if (operator.equalsIgnoreCase("STARTS_WITH")) {
				filterRO.setOperator(Operator.STARTS_WITH);
			} else if (operator.equalsIgnoreCase("ENDS_WITH")) {
				filterRO.setOperator(Operator.ENDS_WITH);
			} else if (operator.equalsIgnoreCase("CONTAINS")) {
				filterRO.setOperator(Operator.CONTAINS);
			} else if (operator.equalsIgnoreCase("NOT_CONTAINS")) {
				filterRO.setOperator(Operator.NOT_CONTAINS);
			} else if (operator.equalsIgnoreCase("GT")) {
				filterRO.setOperator(Operator.GT);
			} else if (operator.equalsIgnoreCase("LT")) {
				filterRO.setOperator(Operator.LT);
			}
		}
		filterRO.setValue("value " + index);
		return filterRO;
	}

	public SortRO getSortRO(final int index, final String sortOrder) {
		SortRO sortRO = new SortRO();
		sortRO.setField("sortField " + index);

		if (sortOrder != null && !sortOrder.trim().isEmpty()) {
			if (sortOrder.equalsIgnoreCase("ASC")) {
				sortRO.setOrder(Order.ASC);
			} else if (sortOrder.equalsIgnoreCase("DESC")) {
				sortRO.setOrder(Order.DESC);
			} else if (sortOrder.equalsIgnoreCase("NONE")) {
				sortRO.setOrder(Order.NONE);
			}
		}
		return sortRO;
	}

	public static void main(String args[]) {
		JavaToJSON javaToJSON = new JavaToJSON();
		javaToJSON.run();
	}
}
