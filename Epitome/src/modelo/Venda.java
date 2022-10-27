package modelo;

import java.sql.Date;

public class Venda {
		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
		public int getFk() {
		return fk;
	}
	public void setFk(int fk) {
		this.fk = fk;
	}
		private int id;
		private float total;
		private String pagamento;
		Date dataVenda;
		int quantidade;
		private int fk;
}
