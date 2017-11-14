package ve.org.bcv.reportes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.google.common.io.Files;

import ve.org.bcv.dto.ResumenMensualDto;

public class ExcelEstadistica {

	/**
	 * Programa de ejemplo para la creacion de un archivo de excel
	 * 
	 * El programa simulara un reporte anual mostrando los porcentajes de cada
	 * dia del a�o (simulados via numeros aleatorios) en el formato:
	 * 
	 * <nombre mes> <dia 1> <dia 2> <dia 3>...
	 * 
	 * Cada fila tendra el largo correspondiente a los dias de dicho posicion
	 * 
	 * @param args
	 */
	public InputStream procesar(Map<String, ResumenMensualDto> cadaDiaSuNumeAsistenciaByMes) {
		File archivo = null;
		InputStream targetStream = null;
		/**
		 * Por cada NbHorario , Obtenemos la colleccionde numero de dias y cada
		 * dia tiene su asistencia total
		 * key=diaName+"|"+diaMonth+"|"+atencionIndividual.getId().getNbHorario(
		 * );
		 */

		List<String> nomHorariosList = new ArrayList<String>();

		/**
		 * Para cada nombrede hoarrio.. tiene su numero de dias ya contyados
		 * Ejemplo Example lunes|19|tardeFemenino tiene 12 duis asistencia para
		 * el nombre tardeFemenino, dia lunes fecha 19
		 **/
		Map<String, List<ResumenMensualDto>> resumenPorDiaCadaNombreHorarioKeyList = new HashMap<String, List<ResumenMensualDto>>();

		Iterator it = cadaDiaSuNumeAsistenciaByMes.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();

			StringTokenizer stk = new StringTokenizer(key, "|");
			String diaName = stk.nextToken();
			String diaMonth = stk.nextToken();
			String NbHorario = stk.nextToken();
			/** Searamos p�r nombre de horario */
			if (!resumenPorDiaCadaNombreHorarioKeyList.containsKey(NbHorario)) {

				/**
				 * Por cada nombre de horario... tenemos los dias y por cada
				 * dia.. tenemos un nuemro de asistencias
				 */
				resumenPorDiaCadaNombreHorarioKeyList.put(NbHorario, new ArrayList<ResumenMensualDto>());
				/** Nombres de los horarios para la lista de excel */
				nomHorariosList.add(NbHorario);
			}

			/**
			 * Por cada nombre de horario... tenemos los dias y por cada dia..
			 * tenemos un nuemro de asistencias
			 */
			List<ResumenMensualDto> resumenPorDiaCadaNombreHorarioLst = resumenPorDiaCadaNombreHorarioKeyList
					.get(NbHorario);
			/** Tenemops el dia y el numero de asistencia por ese dia */
			/**pueden haber varios dias para el mismo nombre de horario.... cada dia es un
			 * objeto que va a una lista del no,bre de  horario*/
			resumenPorDiaCadaNombreHorarioLst.add(cadaDiaSuNumeAsistenciaByMes.get(key));
			/**
			 * Guardamos el horario y la lista de numero de dias y cada dia el
			 * total de asistencias
			 */
			resumenPorDiaCadaNombreHorarioKeyList.put(NbHorario, resumenPorDiaCadaNombreHorarioLst);

		}

	 

		String[] nomHorariosArrayToExcel = new String[nomHorariosList.size()];
		nomHorariosArrayToExcel = nomHorariosList.toArray(nomHorariosArrayToExcel);
		/**
		 * End Resumen de cada cedula, el dia que se atendio, el numero del dia
		 * del mes Guardamos por el nombre de horario, el nombre del dia y el
		 * numero deldia.. la data que asistio
		 */

		// Creamos nuestro libro de excel
		HSSFWorkbook workbook = new HSSFWorkbook();

		// Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
		HSSFSheet sheet = workbook.createSheet("Reporte");

		// Un arreglo con los nombres de los meses del a�o
		// Un arreglo con la duraci�n de cada mes
		Integer[] diasMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		// String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
		// "Junio", "Julio", "Agosto", "Septiembre",
		// "Octubre", "Noviembre", "Diciembre" };

		// Creamos un formato para las celdas
		//HSSFCellStyle style = workbook.createCellStyle();
		// Especificamente que *siempre* muestre dos digitos enteros y dos
		// decimales
		// style.setDataFormat(workbook.createDataFormat().getFormat("00.00"));

		// Este ciclo ira creando las *filas*, una por cada mes del a�o
		// la variable posicion indicara en que fila nos encontramos, mientras
		// que mes
		// selecionara el nombre del mes.
		// posicion comienza en 1 para compensar que ya usarmos la fila 0 para
		// poner los dias
		int colEncabezado = 0;
		int posicion = 1;
		for (int mes = 0; mes < nomHorariosArrayToExcel.length; mes++) {

			// Creamos una fila en la posicion indicada por el contador del
			// ciclo
			Row fila = sheet.createRow(posicion);

			// Creamos la celda para el nombre del mes, en la primera posicion
			// de la fila
			Cell celdaMes = fila.createCell(0);
			// Indicamos que valor debe tener
			celdaMes.setCellValue(nomHorariosArrayToExcel[mes]);

			// Este ciclo ira llenando las *celdas* en la fila que acabamos de
			// crear, llenando solo el numero de celdas indicado por el numero
			// de dias

			// Empezamos en 1 y le sumamos 1 a los dias del posicion para
			// compensar
			// que la celda 0 la usamos para el nombre del posicion

			List<ResumenMensualDto> resumenPorDiaCadaNombreHorarioList = resumenPorDiaCadaNombreHorarioKeyList
					.get(nomHorariosArrayToExcel[mes]);

			boolean swUnaSolaVez = false;
			 Calendar c = Calendar.getInstance();
			 
			for (ResumenMensualDto resumenMensualDto : resumenPorDiaCadaNombreHorarioList) {
                
				/** Encabezado dia de los meses */
				if (!swUnaSolaVez) {
					// En la primera fila ponemos el numero del dia
					Row encabezado = sheet.createRow(colEncabezado);
					// i empieza en 1 para alinear el numero de dias con los
					// datos ya que
					// la columna 0 se usara para el nombre del posicion
					int j = 0;
					
					for (int day_of_week = 1; day_of_week < diasMes[resumenMensualDto.getDiaMonth()]; day_of_week++) {
						Cell celda = encabezado.createCell(day_of_week);
						ResumenMensualDto resumenMensualDtoDia = null;
						for (ResumenMensualDto resumenMensualDtoAux : resumenPorDiaCadaNombreHorarioList) {
							if (resumenMensualDtoAux.getDiaMonth() == day_of_week) {
								resumenMensualDtoDia = resumenMensualDtoAux;
							}

						}
						String diaName = "";
						if (null != resumenMensualDtoDia) {
                            c.set(resumenMensualDtoDia.getYear(), resumenMensualDtoDia.getCurrMonth(), day_of_week, 0, 0); 
                        	diaName = resumenMensualDtoDia.getStrDays()[c.get(Calendar.DAY_OF_WEEK) - 1];
						} 

						celda.setCellValue(diaName+" "+ (day_of_week));
					}
					swUnaSolaVez = true;
				}

				// Creamos una celda en posicion indicada de la fila en la que
				// estamos trabajando
				Cell celda = fila.createCell(resumenMensualDto.getDiaMonth());
				// Indicamos el formato que la celda usara
				//celda.setCellStyle(style);
				// Almacenamos el porcentaje en la celda del dia
				celda.setCellValue(resumenMensualDto.getNumAsistencia());

			}
			// Al momento que el ciclo de otra vuelta el valor de posicion se
			// incrementara
			// de modo que al crear una nueva fila, esta estara ubicada en la
			// *siguiente* fila y no sobre escribira la que hemos creado

			colEncabezado += 4;
			posicion = colEncabezado + 1;

		}

		// Ahora almacenaremos el archivo en disco
		try {
			URL url = ExcelEstadistica.class.getResource("ejemplo.xls");
			File source = new File(url.getPath());
			File dest = File.createTempFile("salida", ".xls");
			copyFileUsingApacheCommonsIO(  source,  dest);
	    	   System.out.println("Temp file : " + dest.getAbsolutePath());
			
			archivo = dest;//new File(path);
			FileOutputStream out = new FileOutputStream(archivo);
			workbook.write(out);
			out.close();
			targetStream = Files.asByteSource(archivo).openStream();
		} catch (IOException e) {
			System.err.println("ERROR AL CREAR EL ARCHIVO!");
			e.printStackTrace();
		}
		System.out.println("Reporte generado");
		return targetStream;
	}
	
	private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
	    FileUtils.copyFile(source, dest);
	}
	 
}
