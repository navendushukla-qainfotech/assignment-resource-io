package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;

import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader {

	JSONObject jsonObj;
	List<Individual> listind;
	List<Team> listteam;

	public TeamsJsonReader() {
		
	/** to read the json file and to form individual list and team list */	
		
		
		try {
			FileReader fr = new FileReader(new File(
					"/home/navendushukla/Downloads/assignment-resource-io-master/src/test/resources/db.json"));
			JSONParser parser = new JSONParser();
			//Object ob = parser.parse(fr);
			JSONObject jo = (JSONObject)parser.parse(fr);
			listind = new ArrayList<Individual>();
			JSONArray ja = (JSONArray) jo.get("individuals");
			JSONObject job[] = new JSONObject[ja.size()];
			for (int i = 0; i < ja.size(); i++) {
				job[i] = (JSONObject) ja.get(i);
				Integer id = ((Long) job[i].get("id")).intValue();
				String name = job[i].get("name").toString();
				Boolean active = (Boolean) job[i].get("active");
				Map<String, Object> map = new HashMap();
				map.put("id", id);
				map.put("name", name);
				map.put("active", active);
				Individual ind = new Individual(map);
				listind.add(ind);

			}
			listteam = new ArrayList<Team>();
			List<Individual> list1;

			JSONArray jaa = (JSONArray) jo.get("teams");
			JSONObject joa[] = new JSONObject[jaa.size()];
			for (int i = 0; i < jaa.size(); i++) {
				list1 = new ArrayList<Individual>();
				joa[i] = (JSONObject) jaa.get(i);
				Integer id = ((Long) joa[i].get("id")).intValue();
				String name = joa[i].get("name").toString();

				JSONArray jas = (JSONArray) joa[i].get("members");
				for (int j = 0; j < jas.size(); j++) {
					Integer idd = ((Long) jas.get(j)).intValue();
					Individual ind = getIndividualById(idd);
					list1.add(ind);

				}
				System.out.println();
				Map<String, Object> map = new HashMap();

				map.put("id", id);
				map.put("name", name);
				map.put("members", list1);

				Team tem = new Team(map);
				listteam.add(tem);

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * get a list of individual objects from db json file
	 * 
	 * @return
	 */

	public List<Individual> getListOfIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");

		/*
		 * JSONParser parser = new JSONParser(); JSONObject obj = new
		 * JSONObject(); JSONArray arr = new JSONArray(); List li = new
		 * ArrayList();
		 */
		// listind = new ArrayList<>();
		// JSONArray individualArray = (JSONArray) jsonObj.get("individuals");
		// for (int i = 0; i < individualArray.size(); i++) {
		// JSONObject ob = (JSONObject) individualArray.get(i);
		// Map<String, Object> map = (Map<String, Object>) ob.clone();
		// Individual individual = new Individual(map);
		// listind.add(individual);
		//
		// }
		return listind;

	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");
		listind = getListOfIndividuals();
		Individual ind = null;
		int flag = 0;
		Iterator<Individual> itr = listind.iterator();
		while (itr.hasNext()) {
			ind = itr.next();
			int a = id;
			int b = ind.getId();
			if (a == b) {
				flag = 1;
				break;
			}

		}
		if (flag == 1)
			return ind;
		else
			throw new ObjectNotFoundException("Individual", "id", id.toString());
	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");
		listind = getListOfIndividuals();
		Individual ind = null;
		Iterator<Individual> itr = listind.iterator();
		int flag = 0;

		while (itr.hasNext()) {
			ind = itr.next();
			String a = name;
			String b = ind.getName();

			if (a.equalsIgnoreCase(b)) {
				flag = 1;
				break;
			}

		}
		if (flag == 0)
			throw new ObjectNotFoundException("Individual", "name", name);
		else
			return ind;

	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		listind = getListOfIndividuals();
		List<Individual> listInactive = new ArrayList<Individual>();
		Iterator<Individual> itr = listind.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = false;
			Boolean b = ind.isActive();
			if (a == b) {
				listInactive.add(ind);
			}

		}
		return listInactive;
	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 */
	public List<Individual> getListOfActiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		listind = getListOfIndividuals();
		List<Individual> listActive = new ArrayList<Individual>();
		Iterator<Individual> itr = listind.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = true;
			Boolean b = ind.isActive();
			if (a == b) {
				listActive.add(ind);
			}

		}
		return listActive;
	}

	/**
	 * get a list of team objects from db json
	 * 
	 * @return
	 */
	public List<Team> getListOfTeams() {
		// throw new UnsupportedOperationException("Not implemented.");
		// listteam = new ArrayList<>();
		// JSONArray TeamArray = (JSONArray) jsonObj.get("teams");
		// for (int i = 0; i < TeamArray.size(); i++) {
		// JSONObject ob = (JSONObject) TeamArray.get(i);
		// Map<String, Object> map = (Map<String, Object>) ob.clone();
		// Team team = new Team(map);
		// listteam.add(team);
		// }
		return listteam;

	}
}
