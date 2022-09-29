package modelo;

public class Produto {
	
		private int idProduto;
		private String nomeProduto;
		private String materialProduto;
		private int quantidadeEstoque;
		private String dimensoesProduto;
		private float precoCustoProduto;
		private float precoVendaProduto;
		
		public Produto() {
			// TODO Auto-generated constructor stub
		}
		public Produto(int idProduto, String nomeProduto, String materialProduto, int quantidadeEstoque,
				String dimensoesProduto, float precoCustoProduto, float precoVendaProduto) {
			super();
			this.idProduto = idProduto;
			this.nomeProduto = nomeProduto;
			this.materialProduto = materialProduto;
			this.quantidadeEstoque = quantidadeEstoque;
			this.dimensoesProduto = dimensoesProduto;
			this.setPrecoCustoProduto(precoCustoProduto);
			this.setPrecoVendaProduto(precoVendaProduto);
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
		public String getDimencoesProduto() {
			return dimensoesProduto;
		}
		public void setDimencoesProduto(String dimencoesProduto) {
			this.dimensoesProduto = dimencoesProduto;
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

		
}
