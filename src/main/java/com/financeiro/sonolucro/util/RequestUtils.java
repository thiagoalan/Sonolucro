
package com.financeiro.sonolucro.util;

import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rodrigo Romero
 * @since 07/12/2012
 * @version 1.0
 */
public class RequestUtils implements Serializable
{
    private static final long serialVersionUID = 3733310790633565579L;

    public static HttpServletRequest getRequest()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        return (HttpServletRequest) external.getRequest();
    }
}
