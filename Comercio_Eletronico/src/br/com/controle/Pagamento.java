package br.com.controle;

public class Pagamento {
    private int idPagamento;
    private int idPedido;
    private String dataPagamento;
    private double valorPago;
    private String formaPagamento;
    private String statusPagamento;

    public Pagamento() {
    }

    public Pagamento(int idPedido, double valorPago, String formaPagamento, String statusPagamento) {
        this.idPedido = idPedido;
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = statusPagamento;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
