package br.com.ucsal.meta.agil.dto.alvo;

public class AlvoPerguntaDTO {
	
	/** Nome da pergunta **/
	private String label;
	/** Valor da Resposta **/
	private Integer score;
	/** Peso da Pergunta **/
	private Double peso;
	
	public AlvoPerguntaDTO() {}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}

}