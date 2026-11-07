package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.enums.FormaPagamento;

public class Venda {
private Long id;
private Cliente cliente;
private LocalDateTime datavenda;
private BigDecimal valorTotal;
private FormaPagamento formaPagamento;
private LocalDate dataVencimento;
private String retiradoPor;
public Venda(Long id, 
		Cliente cliente,
		BigDecimal valorTotal, 
		FormaPagamento formaPagamento,
		
		LocalDate dataVencimento,
		String retiradoPor) {
	
	this.id = id;
	this.cliente = cliente;
	this.datavenda = LocalDateTime.now();
	this.valorTotal = valorTotal;
	this.formaPagamento = formaPagamento.FIADO;
	this.dataVencimento = dataVencimento;
	this.retiradoPor = retiradoPor;
}



}
