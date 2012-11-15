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
