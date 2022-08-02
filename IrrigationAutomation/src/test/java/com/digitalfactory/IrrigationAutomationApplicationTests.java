package com.digitalfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.digitalfactory.model.Plot;

public class IrrigationAutomationApplicationTests extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getPlotList() throws Exception {
		String uri = "/api/plots";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(content);
		String data = jsonObject.getString("data");
		Plot[] plotlist = super.mapFromJson(data, Plot[].class);
		assertTrue(plotlist.length > 0);
	}

	@Test
	public void createPlot() throws Exception {
		String uri = "/api/plots";
		Plot plot = new Plot();
		plot.setName("PL62");
		plot.setArea(2000);
		plot.setCropType("Cotton");
		plot.setSoilType("Black");
		plot.setStartTime("09:30:45");
		plot.setEndTime("09:30:45");
		String inputJson = super.mapToJson(plot);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(content);
		String message = jsonObject.getString("message");
		assertEquals(message, "Successfully added data!");
	}

	@Test
	public void updatePlot() throws Exception {
		String uri = "/api/plots/39";
		Plot plot = new Plot();
		plot.setName("PL62");
		plot.setArea(2000);
		plot.setCropType("SugarCane");
		plot.setSoilType("Black");
		plot.setStartTime("09:30:45");
		plot.setEndTime("09:30:45");;
		String inputJson = super.mapToJson(plot);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(content);
		String message = jsonObject.getString("message");
		assertEquals(message, "Updated");
	}

	@Test
	public void deletePlot() throws Exception {
		String uri = "/api/plots/39";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(content);
		String message = jsonObject.getString("message");
		assertEquals(message, "Deleted!");
	}
}
