package br.iyk.entidades;

public class DadosSchemaJson {
	private String idGuia;
	private String keyGuia;

	public DadosSchemaJson(String idGuia, String keyGuia) {
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
}
