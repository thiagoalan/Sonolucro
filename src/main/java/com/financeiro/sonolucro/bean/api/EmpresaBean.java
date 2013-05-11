
package com.financeiro.sonolucro.bean.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Interface para representação das empresas cadastradas no Sonolucro
 * 
 * @since 29/10/2012
 * @author rodrigo
 * @version 1.0.0
 */
public interface EmpresaBean
{
    public Long getId(); 
    
    public void setId(Long id);
    
    public String getNomeFantasia();
    
    public void setNomeFantasia(String nomeFantasia);
    
    public String getCnpj(); 
    
    public void setCnpj(String cnpj);
    
    public Integer getTipo(); //Varejista, Bancaria, etc
    
    public void setTipo(Integer tipo); 
    
    public Boolean getStatus(); //ativa || não
    
    public void setStatus(Boolean status); 
    
    public Boolean getECliente(); //É nosso cliente ?
    
    public void setECliente(Boolean eCliente); 
    
    public CidadeBean getCidade(); 
    
    public void setCidade(CidadeBean cidade); 
    
    public Date getDataCadastro(); 
    
    public void setDataCadastro(Date dataCadastro); 
    
    public Collection<PromocaoBean> getPromocoes(); 
    
    public void setPromocoes(Collection<PromocaoBean> promocoes);
    
    public String getTelefone();
    
    public void setTelefone(String telefone);
    
    public String getEmail(); 
    
    public void setEmail(String email); 
    
    public String getSite(); 
    
    public void setSite(String site); 
    
    public String getDescricao(); 
    
    public void setDescricao(String descricao); 
    
    public Collection<LocalidadeComercialBean> getLocalidadesComercial(); 
    
    public void setLocalidadesComercial(Collection<LocalidadeComercialBean> 
            localidadesComercial); 
    
    public Collection<PessoaBean> getResponsaveisPelaEmpresa(); 
    
    public void setResponsaveisPelaEmpresa(Collection<PessoaBean> responsaveisPelaEmpresa);
}
