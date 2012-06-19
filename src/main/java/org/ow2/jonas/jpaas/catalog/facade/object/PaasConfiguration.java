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

package org.ow2.jonas.jpaas.catalog.facade.object;


import org.w3c.dom.Element;

import java.util.List;

/**
 * Define a Paas Configuration
 * @author David Richard
 */
public class PaasConfiguration implements java.io.Serializable {

    /**
     * Name of the IaasConfiguration.
     */
    private String name;

    /**
     * Type of the IaaS.
     */
    private String type;

    /**
     * SubType of the IaaS.
     */
    private String subType;

    /**
     * True if it's a default IaaS
     */
    private boolean isDefault;

    /**
     * Specific configuration of the IaaS
     */
    private Element specificConfig;

    /**
     * Devops conf of the IaaS (Download Url, Chef role...)
     */
    private Element devopsConf;

    /**
     * The prefix for the resource's name of this IaaS
     */
    private String prefixResourceName;

    /**
     * The IaaS capabilities
     */
    private List<String> capabilities;

    /**
     * Amount of needed port number
     */
    private int portRangeSize;


    public PaasConfiguration(String name, String type, String subType, boolean aDefault, Element specificConfig,
            Element devopsConf, String prefixResourceName, List<String> capabilities, int portRangeSize) {
        this.name = name;
        this.type = type;
        this.subType = subType;
        isDefault = aDefault;
        this.specificConfig = specificConfig;
        this.devopsConf = devopsConf;
        this.prefixResourceName = prefixResourceName;
        this.capabilities = capabilities;
        this.portRangeSize = portRangeSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        isDefault = isDefault;
    }

    public Element getSpecificConfig() {
        return specificConfig;
    }

    public void setSpecificConfig(Element specificConfig) {
        this.specificConfig = specificConfig;
    }

    public Element getDevopsConf() {
        return devopsConf;
    }

    public void setDevopsConf(Element devopsConf) {
        this.devopsConf = devopsConf;
    }

    public String getPrefixResourceName() {
        return prefixResourceName;
    }

    public void setPrefixResourceName(String prefixResourceName) {
        this.prefixResourceName = prefixResourceName;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public int getPortRangeSize() {
        return portRangeSize;
    }

    public void setPortRangeSize(int portRangeSize) {
        this.portRangeSize = portRangeSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IaasConfiguration[name=").append(getName())
                .append(", type=").append(getType())
                .append(", subType=").append(getSubType())
                .append(", isDefault=").append(isDefault())
                .append(", specificConfig=").append(getSpecificConfig().toString())
                .append(", DevopsConf=").append(getDevopsConf().toString())
                .append(", prefixResourceName=").append(getPrefixResourceName())
                .append(", capabilities=").append(getCapabilities().toString())
                .append(", portRangeSize=").append(getPortRangeSize())
                .append("]");
        return sb.toString();
    }
}
