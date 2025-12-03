/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author josem
 */
public class ElementosRenderer {
    // 🔹 Método para renderizar un botón
    public static TableCellRenderer crearBotonRenderer(String texto) {
        return new TableCellRenderer() {
            private final JButton boton = new JButton(texto);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                return boton;
            }
        };
    }

    // 🔹 Método para renderizar un combo box
    public static TableCellRenderer crearComboRenderer(String[] opciones) {
        return new TableCellRenderer() {
            private final JComboBox<String> combo = new JComboBox<>(opciones);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                combo.setSelectedItem(value);
                return combo;
            }
        };
    }
    
}
