/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.farmacia.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory
{
	
	public static Connection conexao(String url, String usuario, String senha)
	{	
	
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());	
			return DriverManager.getConnection(url, usuario, senha);
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		
	}

}