package hr.java.vjezbe.baza.podataka;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.RadSenzora;
import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.entitet.VrstaMjesta;
import hr.java.vjezbe.entitet.Zupanija;
import hr.java.vjezbe.javafx.Main;
import javafx.scene.image.Image;

public class BazaPodataka {

	private static final String DATABASE_FILE = "bazaPodataka.properties";

	public static Connection spajanjeNaBazuPodataka() throws SQLException, IOException {
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));
		String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);
		return veza;
	}

	public static void zatvaranjeVezeNaBazuPodataka(Connection veza) throws SQLException, IOException {
		veza.close();
	}

	public static void spremiDrzavu(Drzava drzava) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertDrzava = veza
					.prepareStatement("INSERT INTO POSTAJE.DRZAVA (NAZIV, POVRSINA) VALUES (?, ?);");
			insertDrzava.setString(1, drzava.getNazivDrzave());
			insertDrzava.setDouble(2, drzava.getPovrsinaDrzave().doubleValue());
			insertDrzava.executeUpdate();
			veza.commit();
			veza.setAutoCommit(true);
			
		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}

	public static List<Drzava> dohvatiDrzavu() throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementDrzave = veza.createStatement();
		ResultSet resultSetDrzave = statementDrzave.executeQuery("SELECT * FROM POSTAJE.DRZAVA");
		List<Drzava> listaDrzava = new ArrayList<>();
		while (resultSetDrzave.next()) {
			Integer drzavaId = resultSetDrzave.getInt("ID");
			String naziv = resultSetDrzave.getString("NAZIV");
			BigDecimal povrsina = new BigDecimal(resultSetDrzave.getDouble("POVRSINA"));
			Drzava drzava = new Drzava(naziv, povrsina);
			drzava.setId(drzavaId);
			listaDrzava.add(drzava);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return listaDrzava;
	}

	public static void spremiZupaniju(Zupanija zupanija) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertZupanija = veza
					.prepareStatement("INSERT INTO POSTAJE.ZUPANIJA (NAZIV, DRZAVA_ID) VALUES (?, ?);");
			insertZupanija.setString(1, zupanija.getNazivZupanije());
			insertZupanija.setInt(2, zupanija.getDrzava().getId());
			insertZupanija.executeUpdate();
			veza.commit();
			veza.setAutoCommit(true);
			
		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}

	public static List<Zupanija> dohvatiZupanije() throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementZupanije = veza.createStatement();
		ResultSet resultSetZupanije = statementZupanije.executeQuery("SELECT * FROM POSTAJE.ZUPANIJA");
		List<Zupanija> listaZupanija = new ArrayList<>();
		while (resultSetZupanije.next()) {
			Integer zupanijaId = resultSetZupanije.getInt("ID");
			String naziv = resultSetZupanije.getString("NAZIV");
			Integer drzavaId = resultSetZupanije.getInt("DRZAVA_ID");
			List<Drzava> listaDrzava = dohvatiDrzavu();
			Zupanija zupanija = new Zupanija(naziv, listaDrzava.get(drzavaId - 1));
			zupanija.setId(zupanijaId);
			listaZupanija.add(zupanija);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return listaZupanija;
	}

	public static void spremiMjesto(Mjesto mjesto) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertMjesto = veza
					.prepareStatement("INSERT INTO POSTAJE.MJESTO (NAZIV, VRSTA, ZUPANIJA_ID) VALUES (?, ?, ?);");
			insertMjesto.setString(1, mjesto.getNazivMjesta());
			insertMjesto.setString(2, mjesto.getVrstaMjesta().toString());
			insertMjesto.setInt(3, mjesto.getZupanija().getId());
			insertMjesto.executeUpdate();
			veza.commit();
			veza.setAutoCommit(true);

		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}

	public static List<Mjesto> dohvatiMjesta() throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementMjesta = veza.createStatement();
		ResultSet resultSetMjesta = statementMjesta.executeQuery("SELECT * FROM POSTAJE.MJESTO");
		List<Mjesto> listaMjesta = new ArrayList<>();
		while (resultSetMjesta.next()) {
			Integer mjestoId = resultSetMjesta.getInt("ID");
			String naziv = resultSetMjesta.getString("NAZIV");
			String vrsta = resultSetMjesta.getString("VRSTA");
			Integer zupanijaId = resultSetMjesta.getInt("ZUPANIJA_ID");
			List<Zupanija> listaZupanija = dohvatiZupanije();
			Mjesto mjesto = new Mjesto(naziv, VrstaMjesta.valueOf(vrsta), listaZupanija.get(zupanijaId - 1));
			mjesto.setId(mjestoId);
			listaMjesta.add(mjesto);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return listaMjesta;
	}

	public static void spremiMjernuPostaju(MjernaPostaja mjernaPostaja) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertPostaja = veza.prepareStatement(
					"INSERT INTO POSTAJE.MJERNA_POSTAJA (NAZIV, MJESTO_ID, LAT, LNG) VALUES (?, ?, ?, ?);");
			insertPostaja.setString(1, mjernaPostaja.getNazivMjernePostaje());
			insertPostaja.setInt(2, mjernaPostaja.getMjesto().getId());
			insertPostaja.setDouble(3, mjernaPostaja.getX().doubleValue());
			insertPostaja.setDouble(4, mjernaPostaja.getY().doubleValue());
			insertPostaja.executeUpdate();
			veza.commit();
			veza.setAutoCommit(true);

		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}
	
	public static List<MjernaPostaja> dohvatiPostajePoMjestu(Integer idMjestaPostaje) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementPostaje = veza.createStatement();
		ResultSet rs;
		PreparedStatement resultSetPostaje = veza.prepareStatement("SELECT * FROM POSTAJE.MJERNA_POSTAJA WHERE MJESTO_ID = ?");
		
		//idMjestaPostaje.setString(1, idMjestaPostaje.toString());
		//idMjestaPostaje.executeQuery();
		rs = resultSetPostaje.executeQuery(); 
		List<MjernaPostaja> listaPostaja = new ArrayList<>();
		while (rs.next()) {
			Integer postajaId = rs.getInt("ID");
			String naziv = rs.getString("NAZIV");
			Integer mjestoId = rs.getInt("MJESTO_ID");
			BigDecimal x = new BigDecimal(rs.getDouble("LAT"));
			BigDecimal y = new BigDecimal(rs.getDouble("LNG"));
			List<Mjesto> listaMjesta = dohvatiMjesta();
			MjernaPostaja mjernaPostaja = new MjernaPostaja(naziv, listaMjesta.get(mjestoId - 1), x, y);
			mjernaPostaja.setId(postajaId);
			listaPostaja.add(mjernaPostaja);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return listaPostaja;
	}
	

	public static List<MjernaPostaja> dohvatiPostaje() throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementPostaje = veza.createStatement();
		ResultSet resultSetPostaje = statementPostaje.executeQuery("SELECT * FROM POSTAJE.MJERNA_POSTAJA");
		List<MjernaPostaja> listaPostaja = new ArrayList<>();
		while (resultSetPostaje.next()) {
			Integer postajaId = resultSetPostaje.getInt("ID");
			String naziv = resultSetPostaje.getString("NAZIV");
			Integer mjestoId = resultSetPostaje.getInt("MJESTO_ID");
			BigDecimal x = new BigDecimal(resultSetPostaje.getDouble("LAT"));
			BigDecimal y = new BigDecimal(resultSetPostaje.getDouble("LNG"));
			List<Mjesto> listaMjesta = dohvatiMjesta();
			MjernaPostaja mjernaPostaja = new MjernaPostaja(naziv, listaMjesta.get(mjestoId - 1), x, y);
			mjernaPostaja.setId(postajaId);
			listaPostaja.add(mjernaPostaja);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return listaPostaja;
	}
	
	public static void obrisiPostaju(Integer idPostajeZaBrisanje) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement deletePostajaSenzor = veza.prepareStatement(
					"DELETE FROM POSTAJE.SENZOR WHERE MJERNA_POSTAJA_ID = ?;");
			deletePostajaSenzor.setString(1, idPostajeZaBrisanje.toString());
			deletePostajaSenzor.executeUpdate();
			
			PreparedStatement deletePostaja = veza.prepareStatement(
					"DELETE FROM POSTAJE.MJERNA_POSTAJA WHERE ID = ?;");
			deletePostajaSenzor.setString(1, idPostajeZaBrisanje.toString());
			deletePostajaSenzor.executeUpdate();
			
			deletePostaja.setString(1, idPostajeZaBrisanje.toString());
			deletePostaja.executeUpdate();
			
			veza.commit();
			veza.setAutoCommit(true);

		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}

	public static void spremiSenzor(Senzor senzor, String idSenzora, String slika) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertSenzor = veza.prepareStatement(
					"MERGE INTO POSTAJE.SENZOR(MJERNA_JEDINICA,PRECIZNOST,VRIJEDNOST,RAD_SENZORA,MJERNA_POSTAJA_ID,ID,SLIKA) VALUES (?, ?, ?, ?, ?, ?, ?)");
			insertSenzor.setString(1, senzor.getMjernaJedinicaSenzora());
			insertSenzor.setDouble(2, senzor.getPreciznostSenzora().doubleValue());
			insertSenzor.setDouble(3, senzor.getVrijednostSenzora().doubleValue());
			insertSenzor.setString(4, senzor.getRadSenzora().toString());
			insertSenzor.setInt(5, senzor.getMjernaPostaja().getId());
			insertSenzor.setString(6, idSenzora);
			
			//System.out.println(System.getProperty("user.dir"));
			insertSenzor.setString(7, slika);
			
			insertSenzor.executeUpdate();
			veza.commit();
			veza.setAutoCommit(true);

		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}
	
	
	
	
	public static void obrisiSenzor(Integer idSenzoraZaBrisanje) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement deleteSenzor = veza.prepareStatement(
					"DELETE FROM POSTAJE.SENZOR WHERE ID = ?;");
			deleteSenzor.setString(1, idSenzoraZaBrisanje.toString());
			deleteSenzor.executeUpdate();
			veza.commit();
			veza.setAutoCommit(true);

		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}
	
	
	public static void updateSenzor(Senzor senzor) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement updateSenzor = veza.prepareStatement(
					//"UPDATE POSTAJE.SENZOR SET MJERNA_JEDINICA = ?, PRECIZNOST = ?, VRIJEDNOST = ?, RAD_SENZORA = ?, MJERNA_POSTAJA_ID =? WHERE ID = ?;");
					"MERGE INTO POSTAJE.SENZOR(MJERNA_JEDINICA,PRECIZNOST,VRIJEDNOST,RAD_SENZORA,MJERNA_POSTAJA_ID,ID) VALUES (?, ?, ?, ?, ?;");
			updateSenzor.setString(1, senzor.getMjernaJedinicaSenzora());
			updateSenzor.setDouble(2, senzor.getPreciznostSenzora().doubleValue());
			updateSenzor.setDouble(3, senzor.getVrijednostSenzora().doubleValue());
			updateSenzor.setString(4, senzor.getRadSenzora().toString());
			updateSenzor.setString(5, senzor.getMjernaPostaja().getId().toString());
			updateSenzor.setString(6, senzor.getId().toString());
			
			
			//updateSenzor.setString(6, senzor.getId().toString());
			updateSenzor.executeUpdate();
			veza.commit();
			veza.setAutoCommit(true);

		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}
	
	

	public static List<Senzor> dohvatiSenzore() throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		Statement statementSenzora = veza.createStatement();
		ResultSet resultSetSenzora = statementSenzora.executeQuery("SELECT * FROM POSTAJE.SENZOR");
		List<Senzor> listaSenzora = new ArrayList<>();
		while (resultSetSenzora.next()) {
			Integer senzorId = resultSetSenzora.getInt("ID");
			String mjernaJedinica = resultSetSenzora.getString("MJERNA_JEDINICA");
			BigDecimal preziznost = new BigDecimal(resultSetSenzora.getDouble("PRECIZNOST"));
			BigDecimal vrijednost = new BigDecimal(resultSetSenzora.getDouble("VRIJEDNOST"));
			String rad = resultSetSenzora.getString("RAD_SENZORA");
			Integer postajaId = resultSetSenzora.getInt("MJERNA_POSTAJA_ID");
			String slikaSenzora = resultSetSenzora.getString("SLIKA");
			
			
			List<MjernaPostaja> listaPostaja = dohvatiPostaje();
			Senzor senzor = new Senzor(mjernaJedinica, preziznost, vrijednost, RadSenzora.valueOf(rad),
					listaPostaja.get(postajaId - 1),slikaSenzora);
			senzor.setId(senzorId);
			
			listaSenzora.add(senzor);
		}
		zatvaranjeVezeNaBazuPodataka(veza);
		return listaSenzora;
	}
}
