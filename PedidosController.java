/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.PedidosDAO;
import Modelo.Pedidos;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Alonso
 */
public class PedidosController {
    private PedidosDAO pedidosDAO;


    public PedidosController() {
    this.pedidosDAO = new PedidosDAO(); // ← esto inicializa el objeto
    }
    public boolean eliminarPedido(String personalId) {
        return pedidosDAO.eliminarPedido(personalId);
    }
                
}
