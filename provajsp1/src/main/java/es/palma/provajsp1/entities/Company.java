package es.palma.provajsp1.entities;

import java.util.List;

public class Company {
	private String name;
	private List<UserBean> usuaris;

	public Company() {

	}

	public Company(String name, List<UserBean> usuaris) {
		super();
		this.name = name;
		this.usuaris = usuaris;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserBean> getUsuaris() {
		return usuaris;
	}

	public void setUsuaris(List<UserBean> usuaris) {
		this.usuaris = usuaris;
	}

}
