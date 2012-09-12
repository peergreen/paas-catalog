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

import org.ow2.jonas.jpaas.catalog.api.IPaasCatalogFacade;
import org.ow2.jonas.jpaas.catalog.api.PaasCatalogException;
import org.ow2.jonas.jpaas.catalog.api.PaasConfiguration;

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
 * PaasCatalog Singleton Bean
 * @author David Richard
 */
@Singleton(mappedName = "PaasCatalog")
@Startup
@Local(IPaasCatalogFacade.class)
@Remote(IPaasCatalogFacade.class)
public class PaasCatalogFacadeBean implements IPaasCatalogFacade {

    /**
     * PaasConfiguration list
     */
    List<PaasConfiguration> paasConfigurationList;

    private static final String PAAS_CONFIGURATION_FOLDER = System.getProperty("jonas.base") +
            System.getProperty("file.separator") + "conf" + System.getProperty("file.separator") + "catalog" +
            System.getProperty("file.separator") + "paas" + System.getProperty("file.separator");

    /**
     * Load a configuration
     */
    @PostConstruct
    public void loadConfiguration() {
        paasConfigurationList = new LinkedList<PaasConfiguration>();

        Map<String,String> capabilities = new HashMap<String,String>();

        //JOnAS M6 PaasConfiguration Hard-coded
        String specificConfig = PAAS_CONFIGURATION_FOLDER
                + "specificConfig_jonas-full-5_3_0-M6.xml";
        String devopsConf = PAAS_CONFIGURATION_FOLDER
                + "devopsConf_jonas-full-5_3_0-M6.xml";
        PaasConfiguration jonasFullM6 = new PaasConfiguration("jonas-full-5_3_0-M6", "container", "jonas", true,
                specificConfig, devopsConf, "jonas", capabilities, 10);
        paasConfigurationList.add(jonasFullM6);

        //Micro-JOnAS M6 PaasConfiguration Hard-coded
        specificConfig = PAAS_CONFIGURATION_FOLDER
                + "specificConfig_micro-jonas-5_3_0-M6.xml";
        devopsConf = PAAS_CONFIGURATION_FOLDER
                + "devopsConf_micro-jonas-5_3_0-M6.xml";
        PaasConfiguration microJonasM6 = new PaasConfiguration("micro-jonas-5_3_0-M6", "container", "jonas", true,
                specificConfig, devopsConf, "jonas", capabilities, 10);
        paasConfigurationList.add(microJonasM6);

        //JOnAS M7 PaasConfiguration Hard-coded
        specificConfig = PAAS_CONFIGURATION_FOLDER
                + "specificConfig_jonas-full-5_3_0-M7.xml";
        devopsConf = PAAS_CONFIGURATION_FOLDER
                + "devopsConf_jonas-full-5_3_0-M7.xml";
        PaasConfiguration jonasFullM7 = new PaasConfiguration("jonas-full-5_3_0-M7", "container", "jonas", true,
                specificConfig, devopsConf, "jonas", capabilities, 10);
        paasConfigurationList.add(jonasFullM7);

        //Apache Jk PaasConfiguration Hard-coded
        capabilities = new HashMap<String,String>();
        specificConfig = PAAS_CONFIGURATION_FOLDER
                + "specificConfig_apacheJk.xml";
        devopsConf = PAAS_CONFIGURATION_FOLDER
                + "devopsConf_apacheJk.xml";
        PaasConfiguration apacheJk = new PaasConfiguration("apacheJk", "router", "jk", false,
                specificConfig, devopsConf, "apache", capabilities, 5);
        paasConfigurationList.add(apacheJk);
    }

    /**
     * Get all the PaasConfigurations
     *
     * @return a list of PaasConfiguration
     */
    @Override
    public List<PaasConfiguration> getPaasConfigurationList() {
        return  paasConfigurationList;
    }

    /**
     * Get PaasConfigurations of a specific type
     *
     * @param type the type of the PaasConfiguration to retrieve
     * @return a list of PaasConfiguration
     * @throws PaasCatalogException
     */
    @Override
    public List<PaasConfiguration> getPaasConfigurationList(String type) throws PaasCatalogException {
        List<PaasConfiguration> resultList = new LinkedList<PaasConfiguration>();
        for (PaasConfiguration paasConfiguration : paasConfigurationList) {
            if (paasConfiguration.getType().equals(type)) {
                resultList.add(paasConfiguration);
            }
        }
        if (resultList.isEmpty()) {
            throw new PaasCatalogException("There is no PaaS Catalog with the type " + type + ".");
        } else {
            return resultList;
        }
    }

    /**
     * Get a specific PaasConfiguration by its name
     *
     * @param name the name of the PaasConfiguration
     * @return the PaasConfiguration
     * @throws PaasCatalogException
     */
    @Override
    public PaasConfiguration getPaasConfiguration(String name) throws PaasCatalogException {
        PaasConfiguration result = null;
        for (PaasConfiguration paasConfiguration : paasConfigurationList) {
            if (paasConfiguration.getName().equals(name)) {
                result = paasConfiguration;
                break;
            }
        }
        if (result == null) {
            throw new PaasCatalogException("The PaaS Configuration named " + name + " does not exist.");
        } else {
            return result;
        }
    }

    /**
     * Get the default PaasConfiguration name
     *
     * @return the name
     * @throws PaasCatalogException
     */
    @Override
    public String getDefaultPaasConfigurationName() throws PaasCatalogException {
        String result = null;
        for (PaasConfiguration paasConfiguration : paasConfigurationList) {
            if (paasConfiguration.isDefault()) {
                result = paasConfiguration.getName();
                break;
            }
        }
        if (result == null) {
            throw new PaasCatalogException("There is no default PaaS Configuration.");
        } else {
            return result;
        }
    }
}
