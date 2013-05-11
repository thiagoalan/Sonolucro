
package com.financeiro.sonolucro.controle.api;

import com.financeiro.sonolucro.bean.api.LocalidadeComercialBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.util.SonolucroControleException;
import java.util.List;

/**
 * @author rodrigo
 * @since 16/02/013
 * @version 1.0
 */
public interface LocalidadeComercialControle<T extends LocalidadeComercialBean>
{
   public  T salvar(T localidadeComercial) throws SonolucroControleException;  
   
   public T alterar(T localidadeComercial) throws SonolucroControleException; 
   
   public void apagar(T localidadeComercial) throws SonolucroControleException;
   
   public List<T> listar() throws SonolucroControleException; 
  
   public List<PessoaBean> listarResponsaveisPorLocalidadeComercial(T localidadeComercial) throws SonolucroControleException;
}
