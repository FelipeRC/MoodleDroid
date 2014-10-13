package entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Postagem {

	private Integer id;
	private Integer idDiscussao;
	private Integer idForum;
	private Integer idCurso;
	private String mensagem;
	private String titulo;

	public Postagem() {

	}

	public Postagem(Integer id, Integer idDiscussao, Integer idForum,
			Integer idCurso, String mensagem, String titulo) {
		super();
		this.id = id;
		this.idDiscussao = idDiscussao;
		this.idForum = idForum;
		this.idCurso = idCurso;
		this.mensagem = mensagem;
		this.titulo = titulo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdDiscussao() {
		return idDiscussao;
	}

	public void setIdDiscussao(Integer idDiscussao) {
		this.idDiscussao = idDiscussao;
	}

	public Integer getIdForum() {
		return idForum;
	}

	public void setIdForum(Integer idForum) {
		this.idForum = idForum;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
