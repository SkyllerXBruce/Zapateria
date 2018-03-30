package Modelo;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Componentes {

	// Método Para Crear las Propiedades del boton
	public JButton creaBoton(String nombre, int posx, int posy, int ancho, int alto) {
		JButton boton = new JButton(nombre);
		boton.setBounds(posx, posy, ancho, alto);
		boton.setFont(new Font("Serif", Font.ITALIC, 14));
		return boton;
	}

	// Método Para Crear las Propiedades de las Etiquetas
	public JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tamaño) {
		JLabel etiqueta = new JLabel(nombre);
		etiqueta.setBounds(posx, posy, ancho, alto);
		etiqueta.setFont(new Font("Serif", Font.ITALIC, tamaño));
		return etiqueta;
	}

	// Método Para Crear las Propiedades de los Cuadros de Texto
	public JTextField creaCuadroTexto(int posx, int posy, int ancho, int alto, int tamaño) {
		JTextField texto = new JTextField();
		texto.setBounds(posx, posy, ancho, alto);
		texto.setFont(new Font("Serif", Font.ITALIC, tamaño));
		texto.setText("");
		return texto;
	}

	// Método Para Crear las Propiedades de los JPasswordField
	public JPasswordField creaCuadroPassword(int posx, int posy, int ancho, int alto, int tamaño) {
		JPasswordField texto = new JPasswordField();
		texto.setBounds(posx, posy, ancho, alto);
		texto.setFont(new Font("Serif", Font.ITALIC, tamaño));
		texto.setText("");
		return texto;
	}

	// Método Para Crear las Propiedades de los ComboBox, Regresa un ComboBox
	public JComboBox<String> creaComboBox(String[] items, int posx, int posy, int ancho, int alto, int tamaño) {
		JComboBox<String> elementos = new JComboBox<String>(items);
		elementos.setBounds(posx, posy, ancho, alto);
		elementos.setFont(new Font("Serif", Font.ITALIC, tamaño));
		elementos.setSelectedIndex(0);
		return elementos;
	}
}
