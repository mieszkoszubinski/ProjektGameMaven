package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mieszko.projekt.game.Customer;
import mieszko.projekt.game.ItemType;
import mieszko.projekt.game.Game;

public class GameDBManager {

	private PreparedStatement addGameToCustomerStmt;
	private PreparedStatement findAllGameStmt;
	private static Connection conn;

	public GameDBManager(Connection conn) throws SQLException {

		this.conn = conn;

		addMovieToCustomerStmt = conn
				.prepareStatement("INSERT INTO Game (company,"
						+ "available,price,title,item_type_id,customer_id) VALUES(?,?,?,?,?,?)");
	
		findAllGamesStmt = conn.prepareStatement("select * from game");
	}

	

	public List<Game> findAllGames() {

		List<Game> games = new ArrayList<Game>();

		try {
			ResultSet rs = findAllGamesStmt.executeQuery();

			while (rs.next()) {
				Game game = new Game(rs.getString("title"), ItemType.DVD,
						rs.getString("company"), rs.getFloat("price"));
				game.setId(rs.getInt("id"));
				games.add(game);
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
		addGameToCustomerStmt.setString(4, game.getTitle());
		addGameToCustomerStmt.setBoolean(2, true);
		addGameToCustomerStmt.setString(1, game.getCompany());
		addGameToCustomerStmt.setFloat(3, game.getPrice());
		addGameToCustomerStmt.setFloat(6, id);
		addGameToCustomerStmt.setFloat(5, 1);
		
		addGameToCustomerStmt.execute();
	}
}
