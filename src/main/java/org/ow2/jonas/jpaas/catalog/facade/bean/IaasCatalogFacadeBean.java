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

package org.ow2.jonas.jpaas.catalog.facade.bean;

import org.ow2.jonas.jpaas.catalog.facade.api.IIaasCatalogFacade;
import org.ow2.jonas.jpaas.catalog.facade.object.IaasConfiguration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

/**
 * IaasCatalog Singleton Bean
 * @author David Richard
 */
@Singleton
@Startup
public class IaasCatalogFacadeBean implements IIaasCatalogFacade {

    /**
     * IaasConfiguration list
     */
    List<IaasConfiguration> iaasConfigurationList;


    /**
     * Load a configuration
     */
    @PostConstruct
    public void loadConfiguration() throws ParserConfigurationException, IOException, SAXException {
        iaasConfigurationList = new LinkedList<IaasConfiguration>();

        //Sirocco IaasConfiguration Hard-coded
        List<String> capabilities = new LinkedList<String>();
        String specificConfigString = "<specificConfig>" +
                "<end-point>http://www.sirocco.com/api</end-point>" +
                "<api-version>1.0</api-version>" +
                "<identity>bull</identity>" +
                "<credential>bull</credential>" +
                "<hardware-id>small</hardware-id>" +
                "<image-id>vmi-2266c170</image-id>" +
                "</specificConfig>";
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(specificConfigString));
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(inputSource);
        Element specificConfig = doc.getDocumentElement();
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
     */
    @Override
    public String getDefaultIaasConfigurationName() {
        String result = null;
        for (IaasConfiguration iaasConfiguration : iaasConfigurationList) {
            if (iaasConfiguration.isDefault()) {
                result = iaasConfiguration.getName();
                break;
            }
        }
        return result;
    }

    /**
     * Get a specific IaasConfiguration by its name
     *
     * @param name the name of the IaasConfiguration
     * @return the IaasConfiguration
     */
    @Override
    public IaasConfiguration getIaasConfiguration(String name) {
        IaasConfiguration result = null;
        for (IaasConfiguration iaasConfiguration : iaasConfigurationList) {
            if (iaasConfiguration.getName().equals(name)) {
                result = iaasConfiguration;
                break;
            }
        }
        return result;
    }
}
