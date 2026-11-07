package model;

public class Cliente {
private Long id;
private String codigoCliente;
private String nome;
private String whatsapp;
private String empresa;
private boolean ativo;

public Cliente(Long id, String codigoCliente, String nome, String whatsapp, String empresa, boolean ativo) {

	this.id = id;
	this.codigoCliente = codigoCliente;
	this.nome = nome;
	this.whatsapp = whatsapp;
	this.empresa = empresa;
	this.ativo = ativo;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getCodigoCliente() {
	return codigoCliente;
}


public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getWhatsapp() {
	return whatsapp;
}

public void setWhatsapp(String whatsapp) {
	this.whatsapp = whatsapp;
}

public String getEmpresa() {
	return empresa;
}

public void setEmpresa(String empresa) {
	this.empresa = empresa;
}

public boolean isAtivo() {
	return ativo;
}

public void setAtivo(boolean ativo) {
	this.ativo = ativo;
}
 

}
