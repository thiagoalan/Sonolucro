
package com.financeiro.sonolucro.bean.api;

import java.util.Collection;

/**
 * Interface para representar a localidade comercial 
 * de cada empresa.
 * 
 * @author rodrigo
 * @since 16/11/2012
 * @version 1.0
 */
public interface LocalidadeComercialBean
{
    public Long getId(); 
    
    public void setId(Long id); 
    
    public String getNome(); 
    
    public void setNome(String nome); 
    
    public String getLongitude();
    
    public void setLongitude(String latitude); 
    
    public String getLatitude(); 
    
    public void setLatitude(String latitude); 
    
    public CidadeBean getCidade(); 
    
    public void setCidade(CidadeBean cidade);
    
    public String getBairro(); 
    
    public void setBairro(String bairro);
    
    public String getRua(); 
    
    public void setRua(String rua); 
    
    public String getNumero(); 
    
    public void setNumero(String numero); 
    
    public String getComplemento(); 
    
    public void setComplemento(String complemento); 
    
    public String getCep(); 
    
    public void setCep(String cep);
    
    public String getTelefone(); 
    
    public void setTelefone(String telefone); 
    
    public String getEmail(); 
    
    public void setEmail(String email); 
    
    public String getSite(); 
    
    public void setSite(String site); 
    
    public Collection<PessoaBean> getResponsaveisLocalidadesComerciais(); 
    
    public void setResponsavelLocalidadeComercial(Collection<PessoaBean> responsaveisLocalidadesComerciais); 
    
    public EmpresaBean getEmpresa();
    
    public void setEmpresa(EmpresaBean empresa); 
    
    public String getDescricao(); 
    
    public void setDescricao(String descricao); 
    
}
