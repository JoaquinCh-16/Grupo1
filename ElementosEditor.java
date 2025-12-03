/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import Modelo.Producto;
import com.mycompany.proyectomarcos.PedidoNuevo;
import java.awt.Component;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author josem
 */
public class ElementosEditor {

    public static TableCellEditor crearEditorBoton(JTable tablaProductos, JTable tablaBoleta, List<Producto> carrito, PedidoNuevo pedidoFrame) {
        return new DefaultCellEditor(new JCheckBox()) {
            private final JButton button = new JButton("Añadir");
            private boolean clicked;

            {
                button.addActionListener(e -> {
                    if (clicked) {
                        int row = tablaProductos.getSelectedRow();
                        if (row != -1) {
                            Producto producto = (Producto) tablaProductos.getValueAt(row, 0);
                            DefaultTableModel modeloBoleta = (DefaultTableModel) tablaBoleta.getModel();

                            boolean encontrado = false;

                            for (int i = 0; i < modeloBoleta.getRowCount(); i++) {
                                String idExistente = (String) modeloBoleta.getValueAt(i, 0);
                                if (idExistente.equals(producto.getProductoId())) {
                                    // Ya existe → aumentar cantidad
                                    int cantidadActual = (int) modeloBoleta.getValueAt(i, 2);
                                    modeloBoleta.setValueAt(cantidadActual + 1, i, 2);
                                    encontrado = true;
                                    break;
                                }
                            }

                            if (!encontrado) {
                                // No existe → agregar nueva fila con cantidad 1
                                modeloBoleta.addRow(new Object[]{
                                    producto.getProductoId(),
                                    producto.getNombre(),
                                    1, // cantidad inicial
                                    producto.getPrecio()
                                });
                            }

                            carrito.add(producto); // opcional: puedes controlar duplicados si lo deseas
                            pedidoFrame.actualizarTotal();
                            System.out.println("Añadido al carrito: " + producto.getNombre() + " - S/ " + producto.getPrecio());
                        }
                    }
                });
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value,
                    boolean isSelected, int row, int column) {
                clicked = true;
                return button;
            }

            @Override
            public Object getCellEditorValue() {
                return "Añadir";
            }

            @Override
            public boolean stopCellEditing() {
                clicked = false;
                return super.stopCellEditing();
            }
        };
    }

    // 🔹 Editor para combo (fuera del anterior)
    public static TableCellEditor crearEditorCombo(String[] opciones, Consumer<String> onCambioEstado) {
        JComboBox<String> combo = new JComboBox<>(opciones);

        combo.addActionListener(e -> {
            String nuevoEstado = (String) combo.getSelectedItem();
            onCambioEstado.accept(nuevoEstado);
        });

        return new DefaultCellEditor(combo);
    }
}
