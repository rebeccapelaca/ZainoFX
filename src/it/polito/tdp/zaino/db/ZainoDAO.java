package it.polito.tdp.zaino.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.zaino.model.Pezzo;
import it.polito.tdp.zaino.model.Zaino;

public class ZainoDAO {
	
	public List<Zaino> listZaino() {
		String sql = "SELECT id, capacity FROM problema" ;
		
		try {
			Connection c = DBConnect.getConnection() ;
			PreparedStatement statement = c.prepareStatement(sql) ;
			ResultSet res = statement.executeQuery() ;
			
			List<Zaino> list = new ArrayList<Zaino>() ;
			while(res.next()) {
				Zaino z = new Zaino( res.getString("id"), res.getInt("capacity")) ;
				list.add(z) ;
			}
			c.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public static void main(String args[]) {
		ZainoDAO dao = new ZainoDAO() ;
		List<Zaino> list = dao.listZaino() ;
		System.out.println(list);
	}

	public List<Pezzo> listPezzo(Zaino z) {
		String sql = "SELECT id, peso, costo FROM pezzo WHERE id_problema=?" ;
		
		try {
			Connection c = DBConnect.getConnection() ;
			PreparedStatement statement = c.prepareStatement(sql) ;
			statement.setString(1, z.getNome());
			
			ResultSet res = statement.executeQuery() ;
			
			List<Pezzo> list = new ArrayList<Pezzo>() ;
			while(res.next()) {
				Pezzo p = new Pezzo(res.getInt("peso"), res.getInt("costo"), res.getString("id")) ;
				list.add(p) ;
			}
			c.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

}
