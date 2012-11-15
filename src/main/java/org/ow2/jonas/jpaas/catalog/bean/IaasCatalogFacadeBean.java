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

package org.ow2.jonas.jpaas.catalog.bean;

import org.ow2.jonas.jpaas.catalog.api.IIaasCatalogFacade;
import org.ow2.jonas.jpaas.catalog.api.IaasCatalogException;
import org.ow2.jonas.jpaas.catalog.api.IaasConfiguration;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * IaasCatalog Singleton Bean
 * @author David Richard
 */
@Singleton(mappedName = "IaasCatalog")
@Startup
@Local(IIaasCatalogFacade.class)
@Remote(IIaasCatalogFacade.class)
public class IaasCatalogFacadeBean implements IIaasCatalogFacade {

    /**
     * IaasConfiguration list
     */
    List<IaasConfiguration> iaasConfigurationList;

    private static final String IAAS_CONFIGURATION_FOLDER = System.getProperty("jonas.base") +
            System.getProperty("file.separator") + "conf" + System.getProperty("file.separator") + "catalog" +
            System.getProperty("file.separator") + "iaas" + System.getProperty("file.separator");

    /**
     * Load a configuration
     */
    @PostConstruct
    public void loadConfiguration() {
        iaasConfigurationList = new LinkedList<IaasConfiguration>();

        //Sirocco IaasConfiguration Hard-coded
        Map<String,String> capabilities = new HashMap<String,String>();
        String specificConfig = IAAS_CONFIGURATION_FOLDER + "sirocco.xml";
        IaasConfiguration sirocco = new IaasConfiguration("sirocco", "compute", "vmm", true, true,
                specificConfig, "jpaas-SiroccoVM", capabilities);
        iaasConfigurationList.add(sirocco);
    }

    /**
     * Get all the IaasConfigurations
     *
     * @return a list of IaasConfiguration
     */
    @Override
    public List<IaasConfiguration> getIaasConfigurationList() {
        return iaasConfigurationList;
    }

    /**
     * Get the default IaasConfiguration name
     *
     * @return the name
     * @throws IaasCatalogException
     */
    @Override
    public String getDefaultIaasConfigurationName() throws IaasCatalogException {
        String result = null;
        for (IaasConfiguration iaasConfiguration : iaasConfigurationList) {
            if (iaasConfiguration.isDefault()) {
                result = iaasConfiguration.getName();
                break;
            }
        }
        if (result == null) {
            throw new IaasCatalogException("There is no default IaaS Configuration.");
        } else {
            return result;
        }
    }

    /**
     * Get a specific IaasConfiguration by its name
     *
     * @param name the name of the IaasConfiguration
     * @return the IaasConfiguration
     * @throws IaasCatalogException
     */
    @Override
    public IaasConfiguration getIaasConfiguration(String name) throws IaasCatalogException {
        IaasConfiguration result = null;
        for (IaasConfiguration iaasConfiguration : iaasConfigurationList) {
            if (iaasConfiguration.getName().equals(name)) {
                result = iaasConfiguration;
                break;
            }
        }
        if (result == null) {
            throw new IaasCatalogException("The IaaS Configuration named " + name + " does not exist.");
        } else {
            return result;
        }
    }
}
