
package dao;

import controller.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Usuario;

public class DAO {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	private static String CADASTRAR_CLIENTE = " INSERT INTO CLIENTE (ID, NOME, CPFCNPJ, EMAIL, TELEFONE, ENDEREÇO ) VALUES(NULL,?,?,?,?,?)";

	private static String CONSULTAR_CLIENTE = " SELECT * FROM CLIENTE  WHERE ID = ? ";

	private static String ALTERAR_CLIENTE = " UPDATE CLIENTE SET NOME = ? , CPFCNPJ = ?, EMAIL = ?, TELEFONE = ?, ENDEREÇO = ? WHERE ID = ? ";

	private static String EXCLUIR_CLIENTE = " DELETE FROM CLIENTE  WHERE ID = ? ";

	private static String LISTAR_CLIENTE = " SELECT * FROM CLIENTE WHERE 1 = 1 ";

	private static String CONSULTAR_USUARIO = " SELECT NOME, SENHA  " + " FROM USUARIO " + " WHERE NOME = ? "
			+ " AND SENHA = ? ";

	private static String CADASTRAR_USUARIO = " INSERT INTO USUARIO (NOME,SENHA ) VALUES(?,?)";

	public DAO() {

	}

	public void cadastrarCliente(Cliente cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = CADASTRAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			// ID, NOME, CPF/CPNJ, EMAIL, TELEFONE, ENDERECO
			preparedStatement.setString(i++, cliente.getNome());
			preparedStatement.setString(i++, cliente.getCpfCnpj());
			preparedStatement.setString(i++, cliente.getEmail());
			preparedStatement.setString(i++, cliente.getTelefone());
			preparedStatement.setString(i++, cliente.getEndereco());

			preparedStatement.execute();
			connection.commit();
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso ");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fecharConexao();
		}

	}

	public Cliente consultarCliente(String id) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = CONSULTAR_CLIENTE;
		Cliente cliente = null;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			// ID, NOME, CPF/CPNJ, EMAIL, TELEFONE, ENDERECO
			preparedStatement.setString(i++, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// String id, String nome, String email, String cpfCnpj, String telefone, String
				// endereco
				cliente = new Cliente(resultSet.getString("ID"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("cpfCnpj"), resultSet.getString("telefone"),
						resultSet.getString("endereço"));

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fecharConexao();
		}

		{
			if (cliente == null) {

				JOptionPane.showMessageDialog(null, "Não foi possivel localizar o cliente selecionado", "",
						JOptionPane.WARNING_MESSAGE);
				throw new Exception("Não foi possivel localizar o cliente selecionado");
			}
			return cliente;
		}
	}

	public void alterarCliente(String id, Cliente cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = ALTERAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			// ID, NOME, CPFCPNJ, EMAIL, TELEFONE, ENDERECO
			preparedStatement.setString(i++, cliente.getNome());
			preparedStatement.setString(i++, cliente.getCpfCnpj());
			preparedStatement.setString(i++, cliente.getEmail());
			preparedStatement.setString(i++, cliente.getTelefone());
			preparedStatement.setString(i++, cliente.getEndereco());
			preparedStatement.setString(i++, id);

			preparedStatement.execute();
			connection.commit();
			JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso ");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fecharConexao();
		}

	}

	public void excluirCliente(String id) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = EXCLUIR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;

			preparedStatement.setString(i++, id);

			preparedStatement.execute();
			connection.commit();
			JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso ");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fecharConexao();
		}

	}

	public ArrayList<Cliente> listarCliente() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		ArrayList<Cliente> clientes = new ArrayList<>();
		String query = LISTAR_CLIENTE;

		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// String id, String nome, String email, String cpfCnpj, String telefone, String
				// endereco
				clientes.add(new Cliente(resultSet.getString("id"), resultSet.getString("nome"),
						resultSet.getString("email"), resultSet.getString("cpfCnpj"), resultSet.getString("telefone"),
						resultSet.getString("endereço")));

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fecharConexao();
		}

		{
			if (clientes.size() < 0) {

				JOptionPane.showMessageDialog(null, "Nao ha clientes cadastrados ", "", JOptionPane.WARNING_MESSAGE);
				throw new Exception("Nao ha clientes cadastrados");
			}
			return clientes;
		}
	}

	public Usuario consultarUsuario(String nome, String senha) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		Usuario usuario = null;
		String query = CONSULTAR_USUARIO;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;

			preparedStatement.setString(i++, nome);
			preparedStatement.setString(i++, senha);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				usuario = new Usuario(resultSet.getString("NOME"),
						resultSet.getString("SENHA"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Usuário não cadastrado ", "",
					JOptionPane.WARNING_MESSAGE);
			throw new Exception("Usuário não cadastrado");
		}
		return usuario;

	}

	public void cadastrarUsuario(Usuario usuario) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = CADASTRAR_USUARIO;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;
			// NOME e SENHA
			preparedStatement.setString(i++, usuario.getNome());
			preparedStatement.setString(i++, usuario.getSenha());

			preparedStatement.execute();
			connection.commit();
			JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso ");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fecharConexao();
		}

	}

	private void fecharConexao() {
		try {
			if (resultSet != null) {
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			Conexao.getInstancia().fecharConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
