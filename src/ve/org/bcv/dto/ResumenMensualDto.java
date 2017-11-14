package ve.org.bcv.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ResumenMensualDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nbModulo;
	private String nbGrupo;
	private String nbSubGrupo;
	private String nbActividad;
	private String nbHorario;
	private String nbHorarioAux;
	private String diaName;
	private int diaMonth;
	private int currMonth;
	private long cedula;
	private int numAsistencia;
	private Calendar cal;
	private String[] strDays ;
	private int year;
	
	
	
	
	public ResumenMensualDto(String nbHorarioAux, String diaName, int diaMonth,int currMonth, int numAsistencia) {
		super();
		this.nbHorarioAux = nbHorarioAux;
		this.diaName = diaName;
		this.diaMonth = diaMonth;
		this.currMonth=currMonth;
		this.numAsistencia = numAsistencia;
	}



	public ResumenMensualDto(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario,
			String nbHorarioAux, String diaName, int diaMonth,int currMonth,int year,Calendar cal,String[] strDays ) {
		super();
		this.nbModulo = nbModulo;
		this.nbGrupo = nbGrupo;
		this.nbSubGrupo = nbSubGrupo;
		this.nbActividad = nbActividad;
		this.nbHorario = nbHorario;
		this.nbHorarioAux = nbHorarioAux;
		this.diaName = diaName;
		this.diaMonth = diaMonth;
		this.currMonth=currMonth;
		this.cal=cal;
		this.strDays=strDays;
		this.year=year;
	}



	public ResumenMensualDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getDiaName() {
		return diaName;
	}
	public void setDiaName(String diaName) {
		this.diaName = diaName;
	}
	public int getDiaMonth() {
		return diaMonth;
	}
	public void setDiaMonth(int diaMonth) {
		this.diaMonth = diaMonth;
	}
	public long getCedula() {
		return cedula;
	}
	public void setCedula(long cedula) {
		this.cedula = cedula;
	}
	 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


 


	public String getNbModulo() {
		return nbModulo;
	}



	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}



	public String getNbGrupo() {
		return nbGrupo;
	}



	public void setNbGrupo(String nbGrupo) {
		this.nbGrupo = nbGrupo;
	}



	public String getNbSubGrupo() {
		return nbSubGrupo;
	}



	public void setNbSubGrupo(String nbSubGrupo) {
		this.nbSubGrupo = nbSubGrupo;
	}



	public String getNbActividad() {
		return nbActividad;
	}



	public void setNbActividad(String nbActividad) {
		this.nbActividad = nbActividad;
	}



	public String getNbHorario() {
		return nbHorario;
	}



	public void setNbHorario(String nbHorario) {
		this.nbHorario = nbHorario;
	}



	public String getNbHorarioAux() {
		return nbHorarioAux;
	}



	public void setNbHorarioAux(String nbHorarioAux) {
		this.nbHorarioAux = nbHorarioAux;
	}



	public int getNumAsistencia() {
		return numAsistencia;
	}



	public void setNumAsistencia(int numAsistencia) {
		this.numAsistencia = numAsistencia;
	}



	public int getCurrMonth() {
		return currMonth;
	}



	public void setCurrMonth(int currMonth) {
		this.currMonth = currMonth;
	}



	public Calendar getCal() {
		return cal;
	}



	public void setCal(Calendar cal) {
		this.cal = cal;
	}



	public String[] getStrDays() {
		return strDays;
	}



	public void setStrDays(String[] strDays) {
		this.strDays = strDays;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}


}
