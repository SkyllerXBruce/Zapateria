package Modelo;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Componentes {

	// Método para Crear las Propiedades del JButton (Tamaño de Letra, Nombre,
	// Posicion, largo y ancho, y el Tipo de Letra), Regresa un JButton
	public JButton creaBoton(String nombre, int posx, int posy, int ancho, int alto) {
		JButton boton = new JButton(nombre);
		boton.setBounds(posx, posy, ancho, alto);
		boton.setFont(new Font("Serif", Font.ITALIC, 14));
		return boton;
	}

	// Método para Crear las Propiedades del JLabel (Tamaño de Letra, Nombre,
	// Posicion, largo y ancho, y el Tipo de Letra), Regresa un JLabel
	public JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tamaño) {
		JLabel etiqueta = new JLabel(nombre);
		etiqueta.setBounds(posx, posy, ancho, alto);
		etiqueta.setFont(new Font("Serif", Font.ITALIC, tamaño));
		return etiqueta;
	}

	// Método para Crear las Propiedades del JTextField (Tamaño de Letra, Nombre,
	// Posicion, largo y ancho, y el Tipo de Letra), Regresa un JTextField
	public JTextField creaCuadroTexto(int posx, int posy, int ancho, int alto, int tamaño) {
		JTextField texto = new JTextField();
		texto.setBounds(posx, posy, ancho, alto);
		texto.setFont(new Font("Serif", Font.ITALIC, tamaño));
		texto.setText("");
		return texto;
	}

	// Método para Crear las Propiedades del JPasswordField (Tamaño de Letra, Nombre,
	// Posicion, largo y ancho, y el Tipo de Letra), Regresa un JPasswordField
	public JPasswordField creaCuadroPassword(int posx, int posy, int ancho, int alto, int tamaño) {
		JPasswordField texto = new JPasswordField();
		texto.setBounds(posx, posy, ancho, alto);
		texto.setFont(new Font("Serif", Font.ITALIC, tamaño));
		texto.setText("");
		return texto;
	}

	// Método para Crear las Propiedades del JComboBox (Tamaño de Letra, Nombre,
	// Posicion, largo y ancho, y el Tipo de Letra), Regresa un JComboBox
	public JComboBox<String> creaComboBox(String[] items, int posx, int posy, int ancho, int alto, int tamaño) {
		JComboBox<String> elementos = new JComboBox<String>(items);
		elementos.setBounds(posx, posy, ancho, alto);
		elementos.setFont(new Font("Serif", Font.ITALIC, tamaño));
		elementos.setSelectedIndex(0);
		return elementos;
	}
}
