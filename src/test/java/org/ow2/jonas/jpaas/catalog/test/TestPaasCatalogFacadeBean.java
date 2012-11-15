/**
 * JPaaS
 * Copyright 2012 Bull S.A.S.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * $Id:$
 */

package org.ow2.jonas.jpaas.catalog.test;


import org.ow2.jonas.jpaas.catalog.api.IPaasCatalogFacade;
import org.ow2.jonas.jpaas.catalog.api.PaasCatalogException;
import org.ow2.jonas.jpaas.catalog.api.PaasConfiguration;
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
        this.iPaasCatalogFacade = (IPaasCatalogFacade) new InitialContext().lookup("PaasCatalog");
    }


    @Test
    public void testGetPaasConfigurationList() {
        List<PaasConfiguration> paasConfigurationList = iPaasCatalogFacade.getPaasConfigurationList();
        Assert.assertEquals(paasConfigurationList.size(), 3, "Test the size of the PaasConfiguration list");
    }

    @Test
    public void testGetDefaultPaasConfigurationName() throws PaasCatalogException {
        String name = iPaasCatalogFacade.getDefaultPaasConfigurationName();
        Assert.assertEquals(name, "jonas-full-5.3.0-M6", "Test the default PaasConfiguration name");
    }

    @Test
    public void testGetPaasConfiguration() throws PaasCatalogException {
        PaasConfiguration paasConfiguration = iPaasCatalogFacade.getPaasConfiguration("apacheJk");
        Assert.assertEquals(paasConfiguration.getName(), "apacheJk", "Test the name of the returned PaasConfiguration");
    }
}
