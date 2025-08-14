package br.iyk.restauraTeste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.iyk.core.Banco;
import br.iyk.core.exceptions.BancoException;

public class RestaurarTeste {
	public static void excluiEncaminhamentoDeGuia(Connection conexao, Long numeroGuia) {
		String sqlExcluiEncaminhamento = "DELETE FROM HSSENCAM WHERE NNUMEGUIA = ?";
		PreparedStatement pstm = null;
		try {
			pstm = conexao.prepareStatement(sqlExcluiEncaminhamento);
			pstm.setLong(1, numeroGuia);
			if (pstm.executeUpdate() > 0) {
				System.out.println("Encaminhamento de guia excluído com sucesso!");
			}
			
		} catch (SQLException e) {
			throw new BancoException(e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstm);
			Banco.closeConnection();
		}
	}
}
