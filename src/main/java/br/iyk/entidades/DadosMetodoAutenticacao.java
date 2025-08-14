package br.iyk.entidades;

public class DadosMetodoAutenticacao {
	String idGuia;
	String keyGuia;
	
	public DadosMetodoAutenticacao(String idGuia, String keyGuia) {
		this.idGuia = idGuia;
		this.keyGuia = keyGuia;
	}

	public String getIdGuia() {
		return idGuia;
	}

	public void setIdGuia(String idGuia) {
		this.idGuia = idGuia;
	}

	public String getKeyGuia() {
		return keyGuia;
	}

	public void setKeyGuia(String keyGuia) {
		this.keyGuia = keyGuia;
	}

	public static String retornaTokenInstall() {
		String TOKEN_INSTALL = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJTb2x1cyIsImlhdCI6MTcyNjA4NDg0NCwic3ViIjoiNzE4Njc3Iiwia2V5X3B1YmxpYyI6IjE3MjYwNzQwNDQ6UkVTVEFTU1VSRUQiLCJrZXlfdHlwZSI6IkEiLCJ1c3VhcmlvIjoiUkVTVEFTU1VSRUQiLCJhdWQiOiI1Q0U0NjNFMi02NUYyLTREM0EtQTEzMy02MDdDMDJGMDM3NUY6NjhCNDRGNkItNDY3Qi00NjJFLThERTQtQzQ5MzRGMUVGN0JBOiJ9.AkZjDmpV0r5dStkkPxWilil2eEM0w2no2M4xaHmi7lIB75Z7eU1s30t_XfTku7Q_O3EBIFycsfEVDS4Xjo5KmA";
		return TOKEN_INSTALL;
	}
}
