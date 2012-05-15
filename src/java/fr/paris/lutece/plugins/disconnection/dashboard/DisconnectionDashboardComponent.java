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
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * The Class DeconnectionDashboardComponent.
 * Insert a small piece of javascript code in admin header.
 */
public class DisconnectionDashboardComponent extends DashboardComponent
{
    private static final String MODEL_WEBAPP_ROOT = "webappRoot";
    private static final String TEMPLATE_DISCONNECTION_DASHBOARD = "admin/plugins/disconnection/disconnection_dashboard.html";
    private static final String PROPERTY_TIMEOUT = "timeout";
    private static final String MODEL_TIMEOUT = "timeout";

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
        Integer timeout = AppPropertiesService.getPropertyInt( PROPERTY_TIMEOUT, 2 );


        Map<String, Object> model = new HashMap<String, Object>( );
        model.put( MODEL_TIMEOUT, timeout );
        model.put( MODEL_WEBAPP_ROOT, AppPathService.getBaseUrl( request ) );
        return AppTemplateService.getTemplate( TEMPLATE_DISCONNECTION_DASHBOARD,
                request.getLocale( ), model ).getHtml( );
    }
}
