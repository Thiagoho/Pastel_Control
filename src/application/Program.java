package application;

import java.util.List;

import model.Cliente;
import respository.ClienteRepository;

public class Program {

	public static void main(String[] args) {
		ClienteRepository repository = new ClienteRepository();
		List<Cliente> lista =  repository.listarTodos();
		
		Cliente cliente1 = new Cliente(1L, "CL1I-001", "Thiago", "34998164408","empresa ACB", true);
		Cliente cliente2 = new Cliente(2L, "CL1I-002", "Thiago", "34998164408","empresa ACB", true);
		Cliente cliente3 = new Cliente(3L, "CL1I-003", "Thiago", "34998164408","empresa ACB", true);
		repository.salvar(cliente1);
		Cliente encontrado = repository.buscarPorCodigo("CL1I-001");
		repository.salvar(cliente2);
		Cliente encontrado2 = repository.buscarPorCodigo("CL1I-002");
		repository.salvar(cliente3);
		Cliente encontrado3 = repository.buscarPorCodigo("CL1I-003");
		if (encontrado != null) {
			System.out.println("Clinete encontrado: " + encontrado.getNome());
		System.out.println("Whatsapp; " + encontrado.getWhatsapp());
		
		} else {
			System.out.println("Cliente não encontrado.");
		}
		for (int i = 0; i < lista.size(); i++) {
			Cliente cliente = lista.get(i);
			System.out.println("ID" + cliente.getId());
			
			System.out.println("Código: " + cliente.getCodigoCliente());
			
			System.out.println("Nome: " + cliente.getNome());
			
			System.out.println("Whatsapp: " + cliente.getWhatsapp());
		
			System.out.println("Empresa: " + cliente.getEmpresa());
			
			System.out.println("----------------------------------------");
			
		}
		for (Cliente cliente : repository.listarTodos()) {

		    System.out.println("ID: " + cliente.getId());
		    System.out.println("Código: " + cliente.getCodigoCliente());
		    System.out.println("Nome: " + cliente.getNome());
		    System.out.println("WhatsApp: " + cliente.getWhatsapp());
		    System.out.println("Empresa: " + cliente.getEmpresa());
		    System.out.println("-------------------------");
		}

	}

}
