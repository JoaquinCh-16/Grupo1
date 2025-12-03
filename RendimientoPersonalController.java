package Controlador;

import DAO.RendimientoPersonalDAO;
import Modelo.RendimientoPersonal;
import java.util.List;

public class RendimientoPersonalController {

    private RendimientoPersonalDAO dao;

    public RendimientoPersonalController() {
        dao = new RendimientoPersonalDAO();
    }

    public List<RendimientoPersonal> obtenerRendimiento() {
        return dao.obtenerRendimiento();
    }
}
