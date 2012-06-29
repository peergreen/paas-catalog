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
import org.ow2.jonas.jpaas.catalog.api.PaasConfiguration;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.LinkedList;
import java.util.List;

/**
 * PaasCatalog Singleton Bean
 * @author David Richard
 */
@Singleton(mappedName = "PaasCatalog")
@Startup
public class PaasCatalogFacadeBean implements IPaasCatalogFacade {

    /**
     * PaasConfiguration list
     */
    List<PaasConfiguration> paasConfigurationList;


    /**
     * Load a configuration
     */
    @PostConstruct
    public void loadConfiguration() {
        paasConfigurationList = new LinkedList<PaasConfiguration>();

        List<String> capabilities = new LinkedList<String>();

        //JOnAS M6 PaasConfiguration Hard-coded
        String specificConfig = System.getProperty("user.home") + System.getProperty("file.separator")
                + "specificConfig_jonas-full-5.3.0-M6.xml";
        String devopsConf = System.getProperty("user.home") + System.getProperty("file.separator")
                + "devopsConf_jonas-full-5.3.0-M6.xml";
        PaasConfiguration jonasFullM6 = new PaasConfiguration("jonas-full-5.3.0-M6", "container", "jonas", true,
                specificConfig, devopsConf, "jonas", capabilities, 10);
        paasConfigurationList.add(jonasFullM6);


        //Micro-JOnAS M6 PaasConfiguration Hard-coded
        specificConfig = System.getProperty("user.home") + System.getProperty("file.separator")
                + "specificConfig_micro-jonas-5.3.0-M6.xml";
        devopsConf = System.getProperty("user.home") + System.getProperty("file.separator")
                + "devopsConf_micro-jonas-5.3.0-M6.xml";
        PaasConfiguration microJonasM6 = new PaasConfiguration("micro-jonas-5.3.0-M6", "container", "jonas", true,
                specificConfig, devopsConf, "jonas", capabilities, 10);
        paasConfigurationList.add(microJonasM6);

        //Apache Jk PaasConfiguration Hard-coded
        capabilities = new LinkedList<String>();
        specificConfig = System.getProperty("user.home") + System.getProperty("file.separator")
                + "specificConfig_apacheJk.xml";
        devopsConf = System.getProperty("user.home") + System.getProperty("file.separator")
                + "devopsConf_apacheJk.xml";
        PaasConfiguration apacheJk = new PaasConfiguration("apacheJk", "router", "jk", false,
                specificConfig, devopsConf, "jk", capabilities, 5);
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
     */
    @Override
    public List<PaasConfiguration> getPaasConfigurationList(String type) {
        List<PaasConfiguration> resultList = new LinkedList<PaasConfiguration>();
        for (PaasConfiguration paasConfiguration : paasConfigurationList) {
            if (paasConfiguration.getType().equals(type)) {
                resultList.add(paasConfiguration);
            }
        }
        return resultList;
    }

    /**
     * Get a specific PaasConfiguration by its name
     *
     * @param name the name of the PaasConfiguration
     * @return the PaasConfiguration
     */
    @Override
    public PaasConfiguration getPaasConfiguration(String name) {
        PaasConfiguration result = null;
        for (PaasConfiguration paasConfiguration : paasConfigurationList) {
            if (paasConfiguration.getName().equals(name)) {
                result = paasConfiguration;
                break;
            }
        }
        return result;
    }

    /**
     * Get the default PaasConfiguration name
     *
     * @return the name
     */
    @Override
    public String getDefaultPaasConfigurationName() {
        String result = null;
        for (PaasConfiguration paasConfiguration : paasConfigurationList) {
            if (paasConfiguration.isDefault()) {
                result = paasConfiguration.getName();
                break;
            }
        }
        return result;
    }
}
