/**
 * 
 */
package br.com.lawoffice.web.mb.caixa;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.lawoffice.caixa.CaixaServiceLocal;
import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.web.mb.AutoCompleteMB;

/**
 * 
 * Manager Bean para página de /caixa/credito.xhtml
 * 
 * @author rduarte
 *
 *
 */

@ManagedBean
public class CreditoMB extends AutoCompleteMB{

	/**
	 * Valor para creditar na {@link Conta} do {@link Cliente} ou do {@link Colaborador}
	 */
	protected BigDecimal valor;
	
	
	/**
	 * Serviço de caixa para realizar o credito
	 */
	@EJB
	protected CaixaServiceLocal caixaService;
	
	
	
	public void creditarCliente(){		
		Conta conta = 
			caixaService.creditar(cliente.getConta(), valor);
		
		addMsgCreditoSucesso(conta);								
	}

	public void creditarColaborador(){
		Conta conta =
			caixaService.creditar(colaborador.getConta(), valor);
		
		addMsgCreditoSucesso(conta);					
	}
	
	
	private void addMsgCreditoSucesso(Conta conta) {
		// TODO: internacionalização
		addMsgInformacao(
				null, 
				"Crédito realizado com sucesso: ",  
				"Saldo Atual =  "  + NumberFormat.getCurrencyInstance().format(conta.getSaldo())
			);		
	}

	

	// >>>> GETS E SETS do MB <<<<
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}	
}
