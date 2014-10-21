package org.magnum.videoup.client;

import com.google.common.base.Objects;


/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */
public class Patient {

	private String patient;

	public Patient() {
	}

	public Patient(String patient) {
		super();
		this.patient = patient;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String name) {
		this.patient = patient;
	}

}
