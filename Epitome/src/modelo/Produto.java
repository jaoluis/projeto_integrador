package modelo;

public class Produto {
	
		private int idProduto;
		private String nomeProduto;
		private String materialProduto;
		private int quantidadeEstoque;
		private String dimensoesProduto;
		private float precoCustoProduto;
		private float precoVendaProduto;
		private int fornecedor;
		private int quantVenda;
		
		public Produto() {
			// TODO Auto-generated constructor stub
		}
		public Produto(int idProduto, String nomeProduto, String materialProduto, int quantidadeEstoque,
				String dimensoesProduto, float precoCustoProduto, float precoVendaProduto, int fornecedor) {
			super();
			this.idProduto = idProduto;
			this.nomeProduto = nomeProduto;
			this.materialProduto = materialProduto;
			this.quantidadeEstoque = quantidadeEstoque;
			this.dimensoesProduto = dimensoesProduto;
			this.setPrecoCustoProduto(precoCustoProduto);
			this.setPrecoVendaProduto(precoVendaProduto);
			this.setFornecedor(fornecedor);
		}
		
		public void mostrar() {
			System.out.println(idProduto);
			System.out.println(nomeProduto);
			System.out.println(materialProduto);
			System.out.println(quantidadeEstoque);
			System.out.println(dimensoesProduto);
			System.out.println(precoCustoProduto);
			System.out.println(precoVendaProduto);
		}

		public int getIdProduto() {
			return idProduto;
		}
		public void setIdProduto(int idProduto) {
			this.idProduto = idProduto;
		}
		public String getNomeProduto() {
			return nomeProduto;
		}
		public void setNomeProduto(String nomeProduto) {
			this.nomeProduto = nomeProduto;
		}
		public String getMaterialProduto() {
			return materialProduto;
		}
		public void setMaterialProduto(String materialProduto) {
			this.materialProduto = materialProduto;
		}
		public int getQuantidadeEstoque() {
			return quantidadeEstoque;
		}
		public void setQuantidadeEstoque(int quantidadeEstoque) {
			this.quantidadeEstoque = quantidadeEstoque;
		}
		public String getDimensoesProduto() {
			return dimensoesProduto;
		}
		public void setDimensoesProduto(String dimensoesProduto) {
			this.dimensoesProduto = dimensoesProduto;
		}
		public float getPrecoCustoProduto() {
			return precoCustoProduto;
		}
		public void setPrecoCustoProduto(float precoCustoProduto) {
			this.precoCustoProduto = precoCustoProduto;
		}
		public float getPrecoVendaProduto() {
			return precoVendaProduto;
		}
		public void setPrecoVendaProduto(float precoVendaProduto) {
			this.precoVendaProduto = precoVendaProduto;
		}
		public int getFornecedor() {
			return fornecedor;
		}
		public void setFornecedor(int fornecedor) {
			this.fornecedor = fornecedor;
		}
		public int getQuantVenda() {
			return quantVenda;
		}
		public void setQuantVenda(int quantVenda) {
			this.quantVenda = quantVenda;
		}

		
}
