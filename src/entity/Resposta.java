package entity;

public class Resposta {

	private Integer id;
	private String resposta;
	private String autor;
	private Integer positivo;
	private Integer negativo;
	private Integer perguntaId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getPositivo() {
		return positivo;
	}

	public void setPositivo(Integer positivo) {
		this.positivo = positivo;
	}

	public Integer getNegativo() {
		return negativo;
	}

	public void setNegativo(Integer negativo) {
		this.negativo = negativo;
	}

	public Integer getPerguntaId() {
		return perguntaId;
	}

	public void setPerguntaId(Integer perguntaId) {
		this.perguntaId = perguntaId;
	}

}
