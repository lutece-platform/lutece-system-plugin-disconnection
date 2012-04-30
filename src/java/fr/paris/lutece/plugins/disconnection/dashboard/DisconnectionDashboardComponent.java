/*
 * Copyright (c) 2002-2011, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.disconnection.dashboard;

import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.dashboard.DashboardComponent;
import fr.paris.lutece.portal.service.i18n.I18nService;

import javax.servlet.http.HttpServletRequest;


/**
 * The Class DeconnectionDashboardComponent.
 * Insert a small piece of javascript code in admin header.
 */
public class DisconnectionDashboardComponent extends DashboardComponent
{
    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.portal.service.dashboard.DashboardComponent#getDashboardData
     * (fr.paris.lutece.portal.business.user.AdminUser,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getDashboardData( AdminUser user, HttpServletRequest request )
    {
        String message = I18nService.getLocalizedString( "disconnection.message", request.getLocale( ) );

        Integer timeout;
        try
        {
            timeout = Integer
                    .parseInt( I18nService.getLocalizedString( "disconnection.timeout", request.getLocale( ) ) );

        }
        catch ( NumberFormatException e )
        {
            timeout = 1;
        }

        StringBuilder stringBuilder = new StringBuilder( );
        stringBuilder.append( "<script type='text/javascript' src='" );
        stringBuilder.append( request.getContextPath( ) );
        stringBuilder
                .append( "/js/plugins/disconnection/disconnection.js'></script><script type='text/javascript'>new DisconnectionMonitor('" );
        stringBuilder.append( message );
        stringBuilder.append( "', " );
        stringBuilder.append( timeout );
        stringBuilder.append( ").check();</script>" );

        return stringBuilder.toString( );
    }
}
