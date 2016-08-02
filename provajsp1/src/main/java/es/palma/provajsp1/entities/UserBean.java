package es.palma.provajsp1.entities;

import java.util.Date;

public class UserBean {

	private String id;
	private String password;
	private String ll1;
	private String ll2;
	private String nom;
	private String tel;
	private Date datnai;
	private String nomComplet;
	public boolean valid;

	public UserBean() {
		
	}
	
	
	public UserBean(String id, String password, String ll1, String ll2, String nom, String tel, Date datnai) {
	  this.id=id;
	  this.password=password;
	  this.ll1=ll1;
	  this.ll2=ll2;
	  this.nom=nom;
	  this.tel=tel;
	  this.datnai=datnai;
	  this.valid=true;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getLl1() {
		return ll1;
	}


	public void setLl1(String ll1) {
		this.ll1 = ll1;
	}


	public String getLl2() {
		return ll2;
	}


	public void setLl2(String ll2) {
		this.ll2 = ll2;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public Date getDatnai() {
		return datnai;
	}


	public void setDatnai(Date datnai) {
		this.datnai = datnai;
	}


	public boolean isValid() {
		return valid;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}


	public String getNomComplet() {
		StringBuilder sb=new StringBuilder();
		sb.append(ll1).append(" ").append(ll2).append(",").append(nom);
		return sb.toString();
	}
	
		
	
}
