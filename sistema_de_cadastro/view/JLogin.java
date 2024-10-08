
package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.DAO;
import model.Usuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//********JLabel e tipo um titulo********
//********JTextField onde o usuario ira escrever ex: senhas, emails e etc..********
//********JButton e literalmente um botao.********
//********JPassWordField usado para senhas********

public class JLogin extends JFrame {
	private JPanel contentPane;
	private JPasswordField passwordFieldSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JLogin() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 333);
		contentPane = new JPanel();
		contentPane.setBackground(Color.gray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(89, 11, 275, 272);
		panel.setBackground(new Color(204, 207, 208));
		contentPane.add(panel);
		panel.setLayout(null);

		// JLabel e tipo um titulo
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setBounds(52, 54, 46, 14);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		panel.add(lblNewLabel);

		JTextField textFieldNomedeUsuario = new JTextField();
		textFieldNomedeUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				// verificar se o enter foi pressionado e pular para a linha de baixo
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordFieldSenha.requestFocus();

				}
			}
		});
		textFieldNomedeUsuario.setBorder(new LineBorder(new Color(171, 173, 179), 2));
		textFieldNomedeUsuario.setBounds(52, 67, 160, 23);
		panel.add(textFieldNomedeUsuario);
		textFieldNomedeUsuario.setColumns(10);

		// quanto maior for o y ele ira para baixo e quanto menor for ele ele ira para
		// cima
		// quanto menor for o valor do x ele vai pra esquerda e quanto maior for o valor
		// ele ira para direita.
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_1.setBounds(52, 116, 46, 14);
		panel.add(lblNewLabel_1);

		// botao de para entrar
		JButton bntNewButtonEntrar = new JButton("ENTRAR");
		bntNewButtonEntrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
					JPrincipal jPrincipal = new JPrincipal();
					jPrincipal.setLocationRelativeTo(jPrincipal);
					jPrincipal.setVisible(true);

				}
			}
		});
		bntNewButtonEntrar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		bntNewButtonEntrar.setFont(new Font("Arial", Font.PLAIN, 11));
		bntNewButtonEntrar.setBounds(91, 174, 89, 23);
		panel.add(bntNewButtonEntrar);

		// evento de click no botao
		bntNewButtonEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DAO dao = new DAO();
				String nome = textFieldNomedeUsuario.getText();
				String senha = new String(passwordFieldSenha.getText());

				if (!"".equalsIgnoreCase(nome) && !"".equalsIgnoreCase(senha)) {
					try {
						Usuario consultarUsuario = dao.consultarUsuario(nome, senha);
						dao.consultarUsuario(consultarUsuario.getNome(), consultarUsuario.getSenha());
						dispose();
						JPrincipal jPrincipal = new JPrincipal();
						jPrincipal.setLocationRelativeTo(jPrincipal);
						jPrincipal.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Confira os campos USUARIO e SENHA");
				}
			}

		});

		JButton btnNewButton = new JButton("CADASTRE-SE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastrarUsuario jcadastrarUsuario = new JCadastrarUsuario();
				jcadastrarUsuario.setLocationRelativeTo(jcadastrarUsuario);
				jcadastrarUsuario.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jcadastrarUsuario.setVisible(true);
			}
		});
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton.setBounds(156, 238, 109, 23);
		panel.add(btnNewButton);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					DAO dao = new DAO();
					String nome = textFieldNomedeUsuario.getText();
					String senha = new String(passwordFieldSenha.getText());

					if (!"".equalsIgnoreCase(nome) && !"".equalsIgnoreCase(senha)) {
						try {
							Usuario consultarUsuario = dao.consultarUsuario(nome, senha);
							dao.consultarUsuario(consultarUsuario.getNome(), consultarUsuario.getSenha());
							dispose();
							JPrincipal jPrincipal = new JPrincipal();
							jPrincipal.setLocationRelativeTo(jPrincipal);
							jPrincipal.setVisible(true);
							bntNewButtonEntrar.requestFocus();
						} catch (Exception e1) {
							e1.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Confira os campos USUARIO e SENHA");
					}

				}
			}
		});
		passwordFieldSenha.setBorder(new LineBorder(new Color(171, 173, 179), 2));
		passwordFieldSenha.setBounds(52, 128, 160, 23);
		panel.add(passwordFieldSenha);

	}
}
