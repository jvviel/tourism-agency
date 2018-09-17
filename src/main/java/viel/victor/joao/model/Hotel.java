package viel.victor.joao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "hotel")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Descrição é obrigatória!")
	@Size(max = 50, message = "A descrição não pode ter mais que 50 caracteres!")
	private String descricao;
	
	@NotEmpty(message = "Quantidade de estrelas é obrigatória!")
	private String estrelas;
	
	@NotNull(message = "Preço diária é obrigatório!")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que R$ 0,01")
	@DecimalMax(value = "999999999.99", message = "Valor não pode ser maior que R$ 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00") //TODO: arrumar mascara, esta duas casas a mais do que cadastrado
	@Column(name = "preco_diaria")
	private Float precoDiaria;
	
	//@NotEmpty(message = "Localização é obrigatória!")
	private String localizacao;
	
	@NotEmpty(message = "Opção de café da manhã é obrigatória!")
	@Column(name = "cafe_manha")
	private String cafeManha;
	
	@NotEmpty(message = "Opção de almoço é obrigatória!")
	private String almoco;
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(String estrelas) {
		this.estrelas = estrelas;
	}

	public Float getPrecoDiaria() {
		return precoDiaria;
	}

	public void setPrecoDiaria(Float precoDiaria) {
		this.precoDiaria = precoDiaria;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getCafeManha() {
		return cafeManha;
	}

	public void setCafeManha(String cafeManha) {
		this.cafeManha = cafeManha;
	}

	public String getAlmoco() {
		return almoco;
	}

	public void setAlmoco(String almoco) {
		this.almoco = almoco;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
