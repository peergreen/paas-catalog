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

import org.ow2.jonas.jpaas.catalog.facade.api.IPaasCatalogFacade;
import org.ow2.jonas.jpaas.catalog.facade.object.PaasConfiguration;
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
 * PaasCatalog Singleton Bean
 * @author David Richard
 */
@Singleton
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
    public void loadConfiguration() throws ParserConfigurationException, IOException, SAXException {
        paasConfigurationList = new LinkedList<PaasConfiguration>();

        List<String> capabilities = new LinkedList<String>();

        //JOnAS M6 PaasConfiguration Hard-coded
        String specificConfigString = "<specificConfig><profile>full</profile><version>5.3.0</version>" +
                "</specificConfig>";
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(specificConfigString));
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(inputSource);
        Element specificConfig = doc.getDocumentElement();

        String devopsConfString = "<devopsconf><url>http://download.forge.objectweb.org/jonas" +
                "/jonas-full-5.3.0-M6-bin.zip</url><chef-role>jonas</chef-role></devopsconf>";
        inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(devopsConfString));
        db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = db.parse(inputSource);
        Element devopsConf = doc.getDocumentElement();

        PaasConfiguration jonasFullM6 = new PaasConfiguration("jonas-full-5.3.0-M6", "container", "jonas", true,
                specificConfig, devopsConf, "jonas", capabilities, 10);
        paasConfigurationList.add(jonasFullM6);


        //Micro-JOnAS M6 PaasConfiguration Hard-coded
        specificConfigString = "<specificConfig><profile>micro</profile><version>5.3.0</version>" +
                "</specificConfig>";
        inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(specificConfigString));
        db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = db.parse(inputSource);
        specificConfig = doc.getDocumentElement();

        devopsConfString = "<devopsconf><url>http://download.forge.objectweb.org/" +
                "jonas/micro-jonas-5.3.0-M6-bin.zip</url><chef-role>jonas</chef-role></devopsconf>";
        inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(devopsConfString));
        db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = db.parse(inputSource);
        devopsConf = doc.getDocumentElement();

        PaasConfiguration microJonasM6 = new PaasConfiguration("micro-jonas-5.3.0-M6", "container", "jonas", true,
                specificConfig, devopsConf, "jonas", capabilities, 10);
        paasConfigurationList.add(microJonasM6);

        //Apache Jk PaasConfiguration Hard-coded
        capabilities = new LinkedList<String>();
        specificConfigString = "<specificConfig><profile>apache-jk</profile><version>2.0-1.2.25</version>" +
                "</specificConfig>";
        inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(devopsConfString));
        db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = db.parse(inputSource);
        specificConfig = doc.getDocumentElement();

        devopsConfString = "<devopsconf></devopsconf>";
        inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(devopsConfString));
        db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = db.parse(inputSource);
        devopsConf = doc.getDocumentElement();
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
