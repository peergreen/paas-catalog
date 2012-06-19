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


import org.ow2.jonas.jpaas.catalog.facade.api.IPaasCatalogFacade;
import org.ow2.jonas.jpaas.catalog.facade.object.PaasConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * PaasCatalogFacade test case
 * @author David Richard
 */
public class TestPaasCatalogFacadeBean {

    /**
     * IPaasCatalog Facade
     */
    private IPaasCatalogFacade iPaasCatalogFacade = null;


    @BeforeClass
    public void init() throws NamingException {
        this.iPaasCatalogFacade = (IPaasCatalogFacade) new InitialContext().lookup("java:global/" +
                "jpaas-catalog-1.0.0-SNAPSHOT/PaasCatalogFacadeBean!" +
                "org.ow2.jonas.jpaas.catalog.facade.api.IPaasCatalogFacade");
    }


    @Test
    public void testGetPaasConfigurationList() {
        List<PaasConfiguration> paasConfigurationList = iPaasCatalogFacade.getPaasConfigurationList();
        Assert.assertEquals(paasConfigurationList.size(), 3, "Test the size of the PaasConfiguration list");
    }

    @Test
    public void testGetDefaultPaasConfigurationName() {
        String name = iPaasCatalogFacade.getDefaultPaasConfigurationName();
        Assert.assertEquals(name, "jonas-full-5.3.0-M6", "Test the default PaasConfiguration name");
    }

    @Test
    public void testGetPaasConfiguration() {
        PaasConfiguration paasConfiguration = iPaasCatalogFacade.getPaasConfiguration("apacheJk");
        Assert.assertEquals(paasConfiguration.getName(), "apacheJk", "Test the name of the returned PaasConfiguration");
    }
}
