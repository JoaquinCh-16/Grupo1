package Modelo;

public class RendimientoPersonal {
    private String vendedor;
    private int numeroPedidos;
    private double ingresoTotal;

    public RendimientoPersonal(String vendedor, int numeroPedidos, double ingresoTotal) {
        this.vendedor = vendedor;
        this.numeroPedidos = numeroPedidos;
        this.ingresoTotal = ingresoTotal;
    }

    public String getVendedor() {
        return vendedor;
    }

    public int getNumeroPedidos() {
        return numeroPedidos;
    }

    public double getIngresoTotal() {
        return ingresoTotal;
    }
}
