
package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.ObjetivoValorGuardadoBean;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Rodrigo Romero
 * @since 30/03/2013
 * @version 1.0
 */
@Entity(name = "ValorGuardado")
@Table(name = "objetivo_valor_guardado")
public class ObjetivoValorGuardadoBeanImpl implements ObjetivoValorGuardadoBean, Serializable
{
    private static final long serialVersionUID = -6950224631483648758L;

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "datavalor", nullable = false)
    private Date dataValor;
    @Column(name = "valor")
    private Float valor;
    @Transient
    private String valorStr; 
    
    public ObjetivoValorGuardadoBeanImpl()
    {
        
    }
    
    public ObjetivoValorGuardadoBeanImpl(Date dataValor, Float valor)
    {
        setDataValor(dataValor); 
        setValor(valor); 
    }

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public Date getDataValor()
    {
        return dataValor;
    }

    @Override
    public void setDataValor(Date dataValor)
    {
        this.dataValor = dataValor;
    }

    @Override
    public Float getValor()
    {
        return valor;
    }

    @Override
    public void setValor(Float valor)
    {
        this.valor = valor;
    }

    @Override
    public String getValorStr()
    {
        return valorStr;
    }

    @Override
    public void setValorStr(String valorStr)
    {
        this.valorStr = valorStr;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 19 * hash + (this.dataValor != null ? this.dataValor.hashCode() : 0);
        hash = 19 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ObjetivoValorGuardadoBeanImpl other = (ObjetivoValorGuardadoBeanImpl) obj;
        if(other.getId() != null)
        {
            if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
                return false;
        }
        if (this.dataValor != other.dataValor && (this.dataValor == null || !this.dataValor.equals(other.dataValor)))
            return false;
        if (this.valor != other.valor && (this.valor == null || !this.valor.equals(other.valor)))
            return false;
        return true;
    }
}