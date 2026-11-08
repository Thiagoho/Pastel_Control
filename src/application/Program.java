package application;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import model.Cliente;
import model.Venda;
import respository.ClienteRepository;
import respository.VendaRepository;
import service.VendaService;

public class Program {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        ClienteRepository clienteRepository =
                new ClienteRepository();

        VendaRepository vendaRepository =
                new VendaRepository();

        VendaService vendaService =
                new VendaService(
                        clienteRepository,
                        vendaRepository
                );

        long proximoIdCliente = 1L;
        int opcao;

        do {

            System.out.println("\n===== PASTELCONTROL =====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar cliente pelo código");
            System.out.println("4 - Registrar venda fiada");
            System.out.println("5 - Listar vendas do cliente");
            System.out.println("6 - Consultar total fiado");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite uma opção válida.");
                opcao = -1;
            }

            try {

                switch (opcao) {

                    case 1: {

                        System.out.println("\n--- CADASTRAR CLIENTE ---");

                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("WhatsApp: ");
                        String whatsapp = scanner.nextLine();

                        System.out.print("Empresa: ");
                        String empresa = scanner.nextLine();

                        String codigo = String.format(
                                "CLI-%03d", proximoIdCliente
                        );

                        Cliente cliente = new Cliente(
                                proximoIdCliente,
                                codigo,
                                nome,
                                whatsapp,
                                empresa,
                                true
                        );

                        clienteRepository.salvar(cliente);
                        proximoIdCliente++;

                        System.out.println("Cliente cadastrado!");
                        System.out.println("ID: " + cliente.getId());
                        System.out.println(
                                "Código: " + cliente.getCodigoCliente()
                        );

                        break;
                    }

                    case 2: {

                        System.out.println("\n--- CLIENTES ---");

                        List<Cliente> clientes =
                                clienteRepository.listarTodos();

                        if (clientes.isEmpty()) {
                            System.out.println("Nenhum cliente cadastrado.");
                        }

                        for (Cliente cliente : clientes) {

                            System.out.println("----------------");
                            System.out.println(
                                    "Código: " + cliente.getCodigoCliente()
                            );
                            System.out.println(
                                    "Nome: " + cliente.getNome()
                            );
                            System.out.println(
                                    "Empresa: " + cliente.getEmpresa()
                            );
                        }

                        break;
                    }

                    case 3: {

                        System.out.print("Código do cliente: ");
                        String codigo = scanner.nextLine().trim();

                        Cliente cliente =
                                clienteRepository.buscarPorCodigo(codigo);

                        if (cliente == null) {
                            System.out.println("Cliente não encontrado.");
                        } else {
                            System.out.println(
                                    "Nome: " + cliente.getNome()
                            );
                            System.out.println(
                                    "WhatsApp: " + cliente.getWhatsapp()
                            );
                            System.out.println(
                                    "Empresa: " + cliente.getEmpresa()
                            );
                        }

                        break;
                    }

                    case 4: {

                        System.out.println("\n--- REGISTRAR VENDA ---");

                        System.out.print("Código do cliente: ");
                        String codigo = scanner.nextLine().trim();

                        System.out.print("Valor da venda: R$ ");
                        BigDecimal valor = new BigDecimal(
                                scanner.nextLine().trim().replace(",", ".")
                        );

                        System.out.print(
                                "Quem retirou? (ENTER = próprio cliente): "
                        );
                        String retiradoPor = scanner.nextLine().trim();

                        if (retiradoPor.isEmpty()) {
                            retiradoPor = null;
                        }

                        Venda venda = vendaService.registrarVenda(
                                codigo,
                                valor,
                                retiradoPor
                        );

                        System.out.println("\nVenda registrada!");
                        System.out.println("ID: " + venda.getId());
                        System.out.println(
                                "Cliente: " + venda.getCliente().getNome()
                        );
                        System.out.println(
                                "Valor: R$ " + venda.getValorTotal()
                        );
                        System.out.println(
                                "Vencimento: " + venda.getDataVencimento()
                        );
                        System.out.println(
                                "Retirado por: " +
                                (venda.getRetiradoPor() == null
                                        ? "Próprio cliente"
                                        : venda.getRetiradoPor())
                        );

                        break;
                    }

                    case 5: {

                        System.out.print("Código do cliente: ");
                        String codigo = scanner.nextLine().trim();

                        List<Venda> vendas =
                                vendaService.listarVendasCliente(codigo);

                        if (vendas.isEmpty()) {
                            System.out.println("Nenhuma venda encontrada.");
                        }

                        for (Venda venda : vendas) {

                            System.out.println("----------------");
                            System.out.println("Venda: " + venda.getId());
                            System.out.println(
                                    "Valor: R$ " + venda.getValorTotal()
                            );
                            System.out.println(
                                    "Data: " + venda.getDataVenda()
                            );
                            System.out.println(
                                    "Vencimento: " + venda.getDataVencimento()
                            );
                        }

                        break;
                    }

                    case 6: {

                        System.out.print("Código do cliente: ");
                        String codigo = scanner.nextLine().trim();

                        BigDecimal total =
                                vendaService.calcularTotalFiado(codigo);

                        System.out.println(
                                "Total fiado: R$ " + total
                        );

                        break;
                    }

                    case 0:
                        System.out.println("Encerrando PastelControl...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (IllegalArgumentException e) {

                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }
}