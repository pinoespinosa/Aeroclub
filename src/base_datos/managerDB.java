package base_datos;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import visual.MainController;


public class managerDB {
	private static Connection connect = null;
	private static Statement statement = null;

	private static boolean ErroresVisibles = true;

	public static boolean executeScript_Void(String Script){
		// Es para ejecutar scripts que no devuelvan resultados, como por ejemplo insertar, actualizar, borrar datos o crear una datos a una tabla. 
		// Retorna un valor booleano que indica si fallo la ejecucion.
		boolean resultado=false;
		if (!Script.equals(""))		
		try
		{
			if (managerDB.conectionWasSucefull())
			{
				statement.execute(Script);
				managerDB.desconectar();
				resultado = true;
			}
		} 
		catch (Exception e)
		{
			if (ErroresVisibles)
				JOptionPane.showMessageDialog(null, e.toString());	
			
		}

		return resultado;
	}
	public static boolean executeScripts_Void(List<String> Script){
		// Es para ejecutar scripts que no devuelvan resultados, como por ejemplo insertar, actualizar, borrar datos o crear una datos a una tabla. 
		// Retorna un valor booleano que indica si fallo la ejecucion.
		boolean resultado=false;
		
		try
		{
			if (managerDB.conectionWasSucefull())
			{
				for (String string : Script) {
					statement.execute(string);	
				}
				
				managerDB.desconectar();
				resultado = true;
			}
		} 
		catch (Exception e)
		{
			if (ErroresVisibles)
				JOptionPane.showMessageDialog(null, e.toString());	
		}

		return resultado;
	}
	public static List<String> executeScript_Query( String Script, String campo_retornar){
		// Es la complementaria de la anterior, funciones que retornan como consultas.

		List<String> resultado = new ArrayList<String>();
		ResultSet resultSet = null;
		try{
			if (managerDB.conectionWasSucefull())
			{
				resultSet = statement.executeQuery(Script);
												
				while (resultSet.next())
					resultado.add(resultSet.getString(campo_retornar));
			}
			managerDB.desconectar();

		} catch (Exception e)
		{
			if (ErroresVisibles)
				JOptionPane.showMessageDialog(null, e.toString());	
			resultado=null;
		}
		return resultado;
	}
	public static List<List<String>> executeScript_Query( String Script,List<String> campo_retornar){
		// Es la complementaria de la anterior, funciones que retornan como consultas.

		List<List<String>> resultado = new ArrayList<List<String>>();
				ResultSet resultSet = null;
		try{
			if (managerDB.conectionWasSucefull())
			{
				resultSet = statement.executeQuery(Script);
				List <String> prov=null;						
				while (resultSet.next())
				{
					prov = new ArrayList<String>();
					for (String list : campo_retornar) {
						prov.add(resultSet.getString(list));
					}
					
				resultado.add(prov);}
			}
			managerDB.desconectar();

		} catch (Exception e)
		{
			if (ErroresVisibles)
				JOptionPane.showMessageDialog(null, e.toString());	
			resultado= null;
		}
		return resultado;
	}

	public static boolean conectionWasSucefull()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");

			// Conectar a la base de datos
			connect = DriverManager.getConnection("jdbc:mysql://localhost/", MainController.getUsuario(), MainController.getPassword());
			connect.setSchema(MainController.getEsquema());
			statement = connect.createStatement();
			return true;
		}

		catch (Exception e) 
		{
			if (ErroresVisibles)
				JOptionPane.showMessageDialog(null, e.toString());				

			return false;
		}
	}
	public static void desconectar()
	{
		try 
		{
			statement.close();
			connect.close();
		} 
		catch (SQLException e) 
		{
			if (ErroresVisibles)
				e.printStackTrace();	
		}
	}

	public static void waitUntisFinishConsole(String path, String nameFile)
	{

		boolean taskDone=false;
		try 
		{
			while(!taskDone)
				try{
					FileReader fR =new FileReader(path + nameFile);
					taskDone=true;
					fR.close();}
				catch ( IOException e1){
					Thread.sleep(1000);}
			Thread.sleep(100);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

