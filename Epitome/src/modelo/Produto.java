package modelo;

public class Produto {
		private int idProduto;
		private String nomeProduto;
		private String materialProduto;
		private int quantidadeEstoque;
		private int dimencoesProduto;
		private float precoProduto;
		
		public Produto(int id, String nome, String material, int quantidade, int dimencoes, float preco) {
			this.idProduto = id;
			this.nomeProduto = nome;
			this.materialProduto = material;
			this.quantidadeEstoque = quantidade;
			this.dimencoesProduto = dimencoes;
			this.precoProduto = preco;
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
		public int getDimencoesProduto() {
			return dimencoesProduto;
		}
		public void setDimencoesProduto(int dimencoesProduto) {
			this.dimencoesProduto = dimencoesProduto;
		}
		public float getPrecoProduto() {
			return precoProduto;
		}
		public void setPrecoProduto(float precoProduto) {
			this.precoProduto = precoProduto;
		}
		
}
