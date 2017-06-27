package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.qainfotech.tap.training.resourceio.TeamsJsonReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members;

	/** used to map json list and java list */

	public Team(Map<String, Object> teamMap) {

		// throw new UnsupportedOperationException("Not implemented.");

		String name1 = null;
		Integer id1 = null;
		List<Individual> members1 = null;

		for (Map.Entry<String, Object> entry : teamMap.entrySet()) {
			if (entry.getKey() == "name")
				name1 = entry.getValue().toString();
			if (entry.getKey() == "id")
				id1 = (Integer) entry.getValue();
			if (entry.getKey() == "members")
				members1 = (List<Individual>) entry.getValue();

		}
		name = name1;
		id = id1;
		members = members1;

	}

	/**
	 * get team name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get team id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return
	 */
	public List<Individual> getMembers() {
		return members;
	}

	/**
	 * get a list of individuals that are members of this team and are also
	 * active
	 * 
	 * @return
	 */
	public List<Individual> getActiveMembers() {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Individual> list1 = new ArrayList<Individual>();
		Iterator<Individual> itr = members.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = true;

			Boolean b = ind.isActive();

			if (a == b) {

				list1.add(ind);
			}

		}

		return list1;
	}

	/**
	 * get a list of individuals that are members of this team but are inactive
	 * 
	 * @return
	 */
	public List<Individual> getInactiveMembers() {
		// throw new UnsupportedOperationException("Not implemented.");

		List<Individual> list1 = new ArrayList<Individual>();

		Iterator<Individual> itr = members.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = false;
			Boolean b = ind.isActive();

			if (a == b) {
				list1.add(ind);
			}

		}
		return list1;
	}
}
