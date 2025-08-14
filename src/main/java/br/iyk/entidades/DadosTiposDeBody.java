package br.iyk.entidades;

public class DadosTiposDeBody {
	private String name;
	private Integer age;

	public DadosTiposDeBody(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
