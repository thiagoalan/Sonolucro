
package com.financeiro.sonolucro.bean.api;

import java.util.Date;

/**
 * Classe para interface todo tipo de usuário humano do sistema
 * 
 * @author rodrigo
 * @since 17/11/2012
 * @version 1.0.0
 * 
 */
public interface PessoaBean
{
    
    public Long getId();
    
    public void setId(Long id); 
    
    public String getNome(); 
    
    public  void setNome(String nome); 
    
    public  String getSobrenome(); 
    
    public  void setSobrenome(String sobrenome); 
    
    public Integer getSexo(); 
    
    public void setSexo(Integer sexo); 
    
    public  String getCpf(); 
    
    public  void setCpf(String cpf); 
    
    public  Date getDataNascimento(); 
    
    public  void setDataNascimento(Date idade); 
    
    public  CidadeBean getCidadeOndeReside(); 
    
    public  void setCidadeOndeReside(CidadeBean cidade); 
    
    public String getEmail(); 
    
    public void setEmail(String email); 
    
    public String getTelefone(); 
    
    public void setTelefone(String telefone); 
}
