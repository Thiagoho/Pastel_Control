package respository;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteRepository {
	private List<Cliente> clientes = new ArrayList<>();
	
	// 1. cadastro cliente
	public void salvar(Cliente cliente) {
		if (clientes == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo.");
		}
		if (buscarPorId(cliente.getId()) != null) {
			throw new IllegalArgumentException("ID do cliente já cadastrado.");
	
		}
		if (existeCodigo(cliente.getCodigoCliente())) {
			throw new IllegalArgumentException("Código do cliente já cadastrado");
		}
		clientes.add(cliente);
	}
	
	
	// 2. Listar todos os clientes
	public List<Cliente> listarTodos() {
		return new ArrayList<>(clientes);
	}
	// 3. Buscar cliente pelo ID
	public Cliente buscarPorId(Long id) {
		for (Cliente cliente : clientes) {
			if (cliente.getId().equals(id)) {
				return cliente;
			}
		}
		return null;
	}
	// 4. Buscar cliente pelo código
	public Cliente buscarPorCodigo(String codigo) {
		for (Cliente cliente : clientes) {
			if (cliente.getCodigoCliente().equals(codigo)) {
				return cliente;
			}
		}
		return null;
	}
	public boolean existeCodigo(String codigo) {
			return buscarPorCodigo(codigo) != null;
		}
	
}
