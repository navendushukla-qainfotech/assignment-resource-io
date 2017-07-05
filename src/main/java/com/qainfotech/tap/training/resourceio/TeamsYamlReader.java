package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.yaml.snakeyaml.Yaml;

import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader {

	List<Individual> listofindividual;
	List<Team> listofteam;
	ClassLoader classLoader;
	String fileName = "db.yaml";
	Map<String, Object> result;

	/**
	 * get a list of individual objects from db yaml file
	 * 
	 * @return
	 */

	public TeamsYamlReader() {

		classLoader = this.getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		Yaml yaml = new Yaml();
		result = (Map<String, Object>) yaml.load(inputStream);

		// File file = new File(classLoader.getResource(fileName).getFile());

		// ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		// try {
		// User user = mapper.readValue(new File("user.yaml"), User.class);
		// System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();}
	}

	public List<Individual> getListOfIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		listofindividual = new ArrayList<>();
		try {
			// InputStream inputStream =
			// classLoader.getResourceAsStream(fileName);
			// Yaml yaml = new Yaml();
			// result = (Map<String, Object>) yaml.load(inputStream);
			ArrayList<Individual> individual = (ArrayList) result.get("individuals");
			Map<String, Object> mapindividual;
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < individual.size(); i++) {
				mapindividual = (Map<String, Object>) individual.get(i);
				map.put("name", mapindividual.get("name"));
				map.put("id", ((Integer) mapindividual.get("id")).intValue());
				map.put("active", mapindividual.get("active"));
				Individual ind = new Individual(map);
				listofindividual.add(ind);
				// System.out.println(mapindividual);
				// System.out.println(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listofindividual;

	}

	/**
	 * Map<String, Object> get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");
		listofindividual = getListOfIndividuals();
		Individual ind = null;
		int flag = 0;
		Iterator<Individual> itr = listofindividual.iterator();
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
		listofindividual = getListOfIndividuals();
		Individual ind = null;
		Iterator<Individual> itr = listofindividual.iterator();
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
			throw new ObjectNotFoundException("Individual", "Name", name);
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
		listofindividual = getListOfIndividuals();
		List<Individual> listInactive = new ArrayList<Individual>();
		Iterator<Individual> itr = listofindividual.iterator();
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
		listofindividual = getListOfIndividuals();
		List<Individual> listActive = new ArrayList<Individual>();
		Iterator<Individual> itr = listofindividual.iterator();
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
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 */
	public List<Team> getListOfTeams() {
		// throw new UnsupportedOperationException("Not implemented.");
		listofteam = new ArrayList<>();
		try {

			InputStream inputStream = classLoader.getResourceAsStream(fileName);
			Yaml yaml = new Yaml();
			Map<String, Object> result = (Map<String, Object>) yaml.load(inputStream);

			List teamm = (List) result.get("teams");
			for (int i = 0; i < teamm.size(); i++) {
				Map<String, Object> team = (Map<String, Object>) teamm.get(i);
				String name = team.get("name").toString();
				int id = (int) team.get("id");
				List teammembers = (List) team.get("members");
				List<Individual> list = new ArrayList<Individual>();
				for (int j = 0; j < teammembers.size(); j++) {
					int idd = (int) teammembers.get(j);
					Individual ind = getIndividualById(idd);
					list.add(ind);

				}
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("name", name);
				map.put("id", id);
				map.put("members", list);
				Team t = new Team(map);
				listofteam.add(t);
				// System.out.println(mapindividual);
				// System.out.println(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listofteam;

	}
}
