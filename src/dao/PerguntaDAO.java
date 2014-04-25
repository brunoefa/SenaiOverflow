package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import entity.Pergunta;

public class PerguntaDAO {
	private Connection connection;

	// Construtor que obtem a conexão da fábrica de conexões com banco de dados;
	public PerguntaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Pergunta pergunta) {
		String sql = "insert into pergunta (pergunta,autor) values (?,?)";
		try {

			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, pergunta.getpergunta());
			stmt.setString(2, pergunta.getautor());

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pergunta> getLista() {
		try {
			List<Pergunta> perguntas = new ArrayList<Pergunta>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from pergunta order by id desc");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Pergunta pergunta = new Pergunta();
				pergunta.setId(rs.getInt("id"));
				pergunta.setpergunta(rs.getString("pergunta"));
				pergunta.setautor(rs.getString("autor"));

				// adicionando o objeto à lista
				perguntas.add(pergunta);
			}

			rs.close();
			stmt.close();
			return perguntas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Pergunta getPergunta(Integer id) {
		try {
			Pergunta pergunta = new Pergunta();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from pergunta where id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				pergunta = new Pergunta();
				pergunta.setId(rs.getInt("id"));
				pergunta.setpergunta(rs.getString("pergunta"));
				pergunta.setautor(rs.getString("autor"));

			}

			rs.close();
			stmt.close();
			return pergunta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
