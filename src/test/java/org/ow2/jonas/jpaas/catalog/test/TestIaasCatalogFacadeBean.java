/**
 * JPaaS
 * Copyright (C) 2012 Bull S.A.S.
 * Contact: jasmine@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * --------------------------------------------------------------------------
 * $Id$
 * --------------------------------------------------------------------------
 */

package org.ow2.jonas.jpaas.catalog.test;


import org.ow2.jonas.jpaas.catalog.api.IIaasCatalogFacade;
import org.ow2.jonas.jpaas.catalog.api.IaasCatalogException;
import org.ow2.jonas.jpaas.catalog.api.IaasConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * IaasCatalogFacade test case
 * @author David Richard
 */
public class TestIaasCatalogFacadeBean {

    /**
     * IIaasCatalog Facade
     */
    private IIaasCatalogFacade iIaasCatalogFacade = null;

    @BeforeClass
    public void init() throws NamingException {
        this.iIaasCatalogFacade = (IIaasCatalogFacade) new InitialContext().lookup("IaasCatalog");
    }


    @Test
    public void testGetIaasConfigurationList() {
        List<IaasConfiguration> iaasConfigurationList = iIaasCatalogFacade.getIaasConfigurationList();
        Assert.assertEquals(iaasConfigurationList.size(), 1, "Test the size of the IaasConfiguration list");
    }

    @Test
    public void testGetDefaultIaasConfigurationName() throws IaasCatalogException {
        String name = iIaasCatalogFacade.getDefaultIaasConfigurationName();
        Assert.assertEquals(name, "sirocco", "Test the default IaasConfiguration name");
    }

    @Test
    public void testGetIaasConfiguration() throws IaasCatalogException {
        IaasConfiguration iaasConfiguration = iIaasCatalogFacade.getIaasConfiguration("sirocco");
        Assert.assertEquals(iaasConfiguration.getName(), "sirocco", "Test the name of the returned IaasConfiguration");
    }
}
