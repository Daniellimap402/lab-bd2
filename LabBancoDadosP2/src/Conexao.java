import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	String url = "jdbc:h2:~/test5";
	String user = "sa";
	String password = "";

	public Connection conectar() {
		Connection connection = null;

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão efetuada");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro: não conectou com banco de dados");
			e.printStackTrace();
		}
		return connection;
	}

	public void desconectar(Connection con) {
		try {
			if ((con != null && !con.isClosed())) {
				con.close();
				System.out.println("A aplicação foi desconectada do Banco de Dados");

			}

		} catch (SQLException e) {
			System.out.println("Erro: não desconectou do banco de dados");
			e.printStackTrace();
		}

	}
}
