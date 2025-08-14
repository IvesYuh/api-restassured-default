package br.iyk.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propriedades {
	
public static Ambiente AMBIENTE = Ambiente.DESENVOLVIMENTO;
	
	public static String versao = "";

	public enum Ambiente {
		JENKINS, DESENVOLVIMENTO
	}
	
	public static String defineVersao() {
		String versaoArquivo = "";
		
		/* Alterar o valor da versaoArquivo para utilizacao local
		 * assim nao daria erro devido a necessidade do arquivo versao.properties.
		 */
		
		if (Propriedades.AMBIENTE == Ambiente.DESENVOLVIMENTO) {
			versaoArquivo = "develop";
		} else {
			Properties pros = loadProperties();
			versaoArquivo = pros.getProperty("versao").trim();
		}
		
		switch (versaoArquivo.trim()) {
		case "develop":
			Propriedades.versao = "teste_projetos";
			break;
		}
		return versaoArquivo.trim();
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream(
				"C:\\Solus\\jenkins\\versao.properties")
		) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new ArquivoException(e.getMessage());
		}
	}
}
