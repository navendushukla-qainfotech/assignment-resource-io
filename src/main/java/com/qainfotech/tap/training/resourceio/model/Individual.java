package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.qainfotech.tap.training.resourceio.TeamsJsonReader;

import java.io.FileReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Individual {

	private final String name;
	private final Integer id;
	private final Boolean active;

	public Individual(Map<String, Object> individualMap) {
		// throw new UnsupportedOperationException("Not implemented.");
		String name1 = null;
		Integer id1 = null;
		Boolean active1 = null;
		for (Map.Entry<String, Object> entry : individualMap.entrySet()) {
			if (entry.getKey() == "name")
				name1 = entry.getValue().toString();
			if (entry.getKey() == "id")
				id1 = (Integer) entry.getValue();
			if (entry.getKey() == "active")
				active1 = (Boolean) entry.getValue();

		}
		name = name1;
		id = id1;
		active = active1;

	}

	/*
	 * public String toString() { return "Individual [name=" + name + ", id=" +
	 * id + ", active=" + active + "]"; }
	 */

	/**
	 * get the name of individual
	 * 
	 * @return individual name
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the employee of of individual
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get the active status of individual
	 * 
	 * @return
	 */
	public Boolean isActive() {
		return active;
	}
}
