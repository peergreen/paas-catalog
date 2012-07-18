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

package org.ow2.jonas.jpaas.catalog.bean;

import org.ow2.jonas.jpaas.catalog.api.IIaasCatalogFacade;
import org.ow2.jonas.jpaas.catalog.api.IaasCatalogException;
import org.ow2.jonas.jpaas.catalog.api.IaasConfiguration;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.LinkedList;
import java.util.List;

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
        List<String> capabilities = new LinkedList<String>();
        String specificConfig = IAAS_CONFIGURATION_FOLDER + "sirocco.xml";
        IaasConfiguration sirocco = new IaasConfiguration("sirocco", "compute", "vmm", true, true,
                specificConfig, "SiroccoVM", capabilities);
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
