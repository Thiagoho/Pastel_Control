
package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Venda;
import respository.ClienteRepository;
import respository.VendaRepository;

public class VendaService {

    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;

    private Long proximoId = 1L;

    public VendaService(
            ClienteRepository clienteRepository,
            VendaRepository vendaRepository) {

        this.clienteRepository = clienteRepository;
        this.vendaRepository = vendaRepository;
    }

    // 1. Registrar uma venda fiada
    public Venda registrarVenda(
            String codigoCliente,
            BigDecimal valorTotal,
            String retiradoPor) {

        Cliente cliente =
                clienteRepository.buscarPorCodigo(codigoCliente);

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente não encontrado."
            );
        }

        validarVenda(cliente, valorTotal);

        LocalDate vencimento = calcularVencimento();

        Venda venda = new Venda(
                proximoId++,
                cliente,
                valorTotal,
                null, vencimento,
                retiradoPor
        );

        vendaRepository.salvar(venda);

        return venda;
    }

    // 2. Calcular o vencimento do fiado
    public LocalDate calcularVencimento() {

        LocalDate hoje = LocalDate.now();

        return hoje.withDayOfMonth(
                hoje.lengthOfMonth()
        );
    }

    // 3. Validar os dados da venda
    private void validarVenda(
            Cliente cliente,
            BigDecimal valorTotal) {

        if (!cliente.isAtivo()) {
            throw new IllegalArgumentException(
                    "Cliente inativo."
            );
        }

        if (valorTotal == null ||
                valorTotal.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "O valor da venda deve ser maior que zero."
            );
        }
    }

    // 4. Listar as vendas de um cliente
    public List<Venda> listarVendasCliente(
            String codigoCliente) {

        Cliente cliente =
                clienteRepository.buscarPorCodigo(codigoCliente);

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente não encontrado."
            );
        }

        List<Venda> resultado = new ArrayList<>();

        for (Venda venda : vendaRepository.listarTodas()) {

            if (venda.getCliente().getId()
                    .equals(cliente.getId())) {

                resultado.add(venda);
            }
        }

        return resultado;
    }

    // 5. Calcular o total fiado do cliente
    public BigDecimal calcularTotalFiado(
            String codigoCliente) {

        BigDecimal total = BigDecimal.ZERO;

        for (Venda venda :
                listarVendasCliente(codigoCliente)) {

            total = total.add(venda.getValorTotal());
        }

        return total;
    }
}
