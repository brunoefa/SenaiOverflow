package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import entity.Resposta;

public class RespostaDAO {
	private Connection connection;

	public RespostaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Resposta resposta) {
		String sql = "insert into resposta (resposta,autor,pergunta_id) values (?,?,?)";
		try {

			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, resposta.getResposta());
			stmt.setString(2, resposta.getAutor());
			stmt.setInt(3, resposta.getPerguntaId());

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Resposta> getLista(Integer id) {
		try {
			List<Resposta> respostas = new ArrayList<Resposta>();
			PreparedStatement stmt = this.connection .prepareStatement("select * from resposta where pergunta_id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Resposta resposta = new Resposta();
				resposta.setResposta(rs.getString("resposta"));
				resposta.setId(Integer.parseInt(rs.getString("id")));
				resposta.setAutor(rs.getString("autor"));
				resposta.setPositivo(rs.getInt("positivo"));
				resposta.setNegativo(rs.getInt("negativo"));
				resposta.setPerguntaId(rs.getInt("id"));

				// adicionando o objeto à lista
				respostas.add(resposta);
			}

			rs.close();
			stmt.close();
			return respostas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Resposta getResposta(Integer id) {
		try {
			Resposta resposta = new Resposta();
			PreparedStatement stmt = this.connection .prepareStatement("select * from resposta where id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				resposta = new Resposta();
				resposta.setId(rs.getInt("id"));
				resposta.setResposta(rs.getString("resposta"));
				resposta.setAutor(rs.getString("autor"));
				resposta.setPositivo(rs.getInt("positivo"));
				resposta.setNegativo(rs.getInt("negativo"));
				resposta.setPerguntaId(rs.getInt("pergunta_id"));
			}

			rs.close();
			stmt.close();
			return resposta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void qualificar(Resposta resposta) {
		String sql = "update resposta set positivo = ?, negativo = ? where id = ?";
		try {

			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, resposta.getPositivo());
			stmt.setInt(2, resposta.getNegativo());
			stmt.setInt(3, resposta.getId());

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
