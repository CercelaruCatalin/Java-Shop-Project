package main;

public class Comanda {
	public String total;
	public String metodaPlata;
	public String nume;
	public String prenume;
	public String nrTel;
	public String loc;
	public String strada;
	public String ap;
	public String desc;
	
	public Comanda() {
		this.total="";
		this.nume="";
		this.prenume="";
		this.ap="";
		this.loc="";
		this.strada="";
		this.ap="";
		this.desc="";
		this.metodaPlata="";
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getNrTel() {
		return nrTel;
	}

	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getStrada() {
		return strada;
	}

	public void setStrada(String strada) {
		this.strada = strada;
	}

	public String getAp() {
		return ap;
	}

	public void setAp(String ap) {
		this.ap = ap;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	

}
