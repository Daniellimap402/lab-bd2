import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consulta {

	public void getClientesPedidos() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectar();

		String consulta = "select c.*,p.id as id_pedido,p.preco,p.data_pedido, p.status as status_pedido, p.forma_pagamento, p.id_cliente from CLIENTE c join PEDIDO p where c.id = p.id_cliente order by c.id;";
		try {
			PreparedStatement stn = conn.prepareStatement(consulta);
			ResultSet resultado = stn.executeQuery();
			Cliente cli = new Cliente();
			Pedido pedido = new Pedido();
			while (resultado.next()) {
				cli.setId(resultado.getLong("id"));
				cli.setNome(resultado.getString("nome"));
				cli.setCpf(resultado.getLong("cpf"));
				cli.setSexo(resultado.getString("sexo"));
				cli.setDataNasc(resultado.getDate("data_nasc"));
				cli.setStatus(resultado.getString("status"));
				pedido.setId(resultado.getLong("id_pedido"));
				pedido.setPreco(resultado.getDouble("preco"));
				pedido.setDataPedido(resultado.getDate("data_pedido"));
				pedido.setStatus(resultado.getString("status_pedido"));
				pedido.setFormaPagamento(resultado.getString("forma_pagamento"));
				pedido.setIdCliente(resultado.getLong("id_cliente"));
				System.out.println("cliente: " + cli.toString());
				System.out.println("pedido: " + pedido.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NÃ£o conseguiu consultar");
		} finally {
			conexao.desconectar(conn);
		}
	}
	
	public void getSomatorioPedidos() {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectar();

		String consulta = "select c.nome,count(p.id) as quant_ped from Cliente c join pedido p where p.id_cliente = c.id group by c.id having count(p.id)>3;";
		try {
			PreparedStatement stn = conn.prepareStatement(consulta);
			ResultSet resultado = stn.executeQuery();
			while (resultado.next()) {
				System.out.println("nome: " + resultado.getString("nome") + ", quantidade pedidos: " + resultado.getLong("quant_ped"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NÃ£o conseguiu consultar");
		} finally {
			conexao.desconectar(conn);
		}
	}

	public void consultar(String nome, int cpf) {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectar();

		String consulta = "select * from cliente WHERE nome  = ? and cpf = ?";

		try {
			PreparedStatement stn = conn.prepareStatement(consulta);

			stn.setString(1, nome);
			stn.setInt(2, cpf);

			ResultSet resultado = stn.executeQuery();
			while (resultado.next()) {
				System.out.print("ID: " + resultado.getInt("ID"));
				System.out.print(" NOME: " + resultado.getString("Nome"));
				System.out.println(" CPF: " + resultado.getInt("CPF"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NÃ£o conseguiu consultar");
		} finally {
			conexao.desconectar(conn);
		}
	}

}
