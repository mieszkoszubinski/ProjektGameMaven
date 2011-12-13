package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sala.patryk.projekt.wypozyczalniavideo.Customer;
import sala.patryk.projekt.wypozyczalniavideo.ItemType;
import sala.patryk.projekt.wypozyczalniavideo.Movie;

public class MovieDBManager {

	private PreparedStatement addMovieToCustomerStmt;
	private PreparedStatement findAllMoviesStmt;
	private static Connection conn;

	public MovieDBManager(Connection conn) throws SQLException {

		this.conn = conn;

		addMovieToCustomerStmt = conn
				.prepareStatement("INSERT INTO MOVIE (director,"
						+ "available,price,title,item_type_id,customer_id) VALUES(?,?,?,?,?,?)");
	
		findAllMoviesStmt = conn.prepareStatement("select * from movie");
	}

	

	public List<Game> findAllGames() {

		List<Game> games = new ArrayList<Game>();

		try {
			ResultSet rs = findAllMoviesStmt.executeQuery();

			while (rs.next()) {
				Game game = new Game(rs.getString("title"), ItemType.DVD,
						rs.getString("company"), rs.getFloat("price"));
				movie.setId(rs.getInt("id"));
				movies.add(game);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return games;
	}

	public void addGameToCustomer(Customer customer, Game game)
			throws SQLException {
		long id = customer.getId();
		addMovieToCustomerStmt.setString(4, game.getTitle());
		addMovieToCustomerStmt.setBoolean(2, true);
		addMovieToCustomerStmt.setString(1, game.getCompany());
		addMovieToCustomerStmt.setFloat(3, game.getPrice());
		addMovieToCustomerStmt.setFloat(6, id);
		addMovieToCustomerStmt.setFloat(5, 1);
		
		addMovieToCustomerStmt.execute();
	}
}
