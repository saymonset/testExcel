package ve.org.bcv.reportes;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.TestCase;
import ve.org.bcv.dto.ResumenMensualDto;

public class ExcelEstadisticaTest extends TestCase {
	
	
	public static void main(String[] args) {
		ExcelEstadistica   excelEstadistica= new ExcelEstadistica();
	    
	    Map<String,ResumenMensualDto> resumenMensualByKey = new HashMap();
	      ResumenMensualDto resumenMensualDto = new ResumenMensualDto();
	    // resumenMensualDto.set
	  	Calendar cal = Calendar.getInstance();
	  	
		cal.setTime(new Date());
	 
		int year=cal.get(Calendar.YEAR);

	  	
	  	Map<String, Integer> cuantosPorCadaUno = new LinkedHashMap<String, Integer>();
	  	String[] strDays =getDiasSemana();
		for (String dia : strDays) {
			cuantosPorCadaUno.put(dia, 0);
		}
	      
			String key="lunes"+"|"+6+"|"+"femenino";
				resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
						"SubGrupo","Actividad",
						"femenino","femenino","lunes",6,10,year,cal,strDays));

				resumenMensualDto=resumenMensualByKey.get(key);
				resumenMensualDto.setNumAsistencia(10);
				resumenMensualByKey.put(key,  resumenMensualDto);
				
				  key="martes"+"|"+7+"|"+"femenino";
				resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
						"SubGrupo","Actividad",
						"femenino","femenino","martes",7,10,year,cal,strDays));

				resumenMensualDto=resumenMensualByKey.get(key);
				resumenMensualDto.setNumAsistencia(9);
				resumenMensualByKey.put(key,  resumenMensualDto);

				
				  key="miercoles"+"|"+8+"|"+"femenino";
					resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
							"SubGrupo","Actividad",
							"femenino","femenino","miercoles",8,10,year,cal,strDays));

					resumenMensualDto=resumenMensualByKey.get(key);
					resumenMensualDto.setNumAsistencia(13);
					resumenMensualByKey.put(key,  resumenMensualDto);
					
					  key="jueves"+"|"+9+"|"+"femenino";
						resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
								"SubGrupo","Actividad",
								"femenino","femenino","jueves",9,10,year,cal,strDays));

						resumenMensualDto=resumenMensualByKey.get(key);
						resumenMensualDto.setNumAsistencia(5);
						resumenMensualByKey.put(key,  resumenMensualDto);
		      
						  key="viernes"+"|"+10+"|"+"femenino";
							resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
									"SubGrupo","Actividad",
									"femenino","femenino","viernes",10,10,year,cal,strDays));

							resumenMensualDto=resumenMensualByKey.get(key);
							resumenMensualDto.setNumAsistencia(9);
							resumenMensualByKey.put(key,  resumenMensualDto);
							
							
						/********************************/
							
							  key="Miercoles"+"|"+1+"|"+"masculino";
							resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
									"SubGrupo","Actividad",
									"masculino","masculino","Miercoles",1,1,year,cal,strDays));

							resumenMensualDto=resumenMensualByKey.get(key);
							resumenMensualDto.setNumAsistencia(10);
							resumenMensualByKey.put(key,  resumenMensualDto);
							
							  key="Jueves"+"|"+2+"|"+"masculino";
							resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
									"SubGrupo","Actividad",
									"masculino","masculino","jueves",2,1,year,cal,strDays));

							resumenMensualDto=resumenMensualByKey.get(key);
							resumenMensualDto.setNumAsistencia(9);
							resumenMensualByKey.put(key,  resumenMensualDto);
							
							  key="Viernes"+"|"+3+"|"+"masculino";
								resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
										"SubGrupo","Actividad",
										"masculino","masculino","miercoles",3,1,year,cal,strDays));

								resumenMensualDto=resumenMensualByKey.get(key);
								resumenMensualDto.setNumAsistencia(13);
								resumenMensualByKey.put(key,  resumenMensualDto);
								
								  key="Lunes"+"|"+6+"|"+"masculino";
									resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
											"SubGrupo","Actividad",
											"masculino","masculino","lunes",6,1,year,cal,strDays));

									resumenMensualDto=resumenMensualByKey.get(key);
									resumenMensualDto.setNumAsistencia(5);
									resumenMensualByKey.put(key,  resumenMensualDto);
					      
									  key="martes"+"|"+28+"|"+"masculino";
										resumenMensualByKey.put(key,  new ResumenMensualDto("Modulo","Grupo",
												"SubGrupo","Actividad",
												"masculino","masculino","martes",28,1,year,cal,strDays));

										resumenMensualDto=resumenMensualByKey.get(key);
										resumenMensualDto.setNumAsistencia(9);
										resumenMensualByKey.put(key,  resumenMensualDto);
							

	      
	      excelEstadistica.procesar(resumenMensualByKey);
	      
	}
	   protected void setUp() throws Exception {
		   
		      super.setUp();
		  
		   }

		   protected void tearDown() throws Exception {
		      super.tearDown();
		      //tardeFemenino, dia lunes fecha 19
		  	/*String diaName = stk.nextToken();
			String diaMonth = stk.nextToken();
			String NbHorario = stk.nextToken();*/
		  
		      
		   }

		 /*  public final void testGetSuma() {
		      //fail("Not yet implemented"); // TODO
		   }

		   public final void testIncrementa() {
		      //fail("Not yet implemented"); // TODO
		   }*/
		   
			public static String[]  getDiasSemana(){
				// Array con los dias de la semana
				String[] strDays = new String[] { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
				return strDays;
			}
}
