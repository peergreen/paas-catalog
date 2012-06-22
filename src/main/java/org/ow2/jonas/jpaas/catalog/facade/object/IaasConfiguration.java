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
 * Define an Iaas Configuration
 * @author David Richard
 */
public class IaasConfiguration implements java.io.Serializable {

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
     * True if the IaaS start automatically
     */
    private boolean autoStart;

    /**
     * Path of the Specific configuration file of the IaaS
     */
    private String specificConfig;

    /**
     * The prefix for the resource's name of this IaaS
     */
    private String prefixResourceName;

    /**
     * The IaaS capabilities
     */
    private List<String> capabilities;


    public IaasConfiguration(String name, String type, String subType, boolean isDefault, boolean autoStart,
            String specificConfig, String prefixResourceName, List<String> capabilities) {
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.isDefault = isDefault;
        this.autoStart = autoStart;
        this.specificConfig = specificConfig;
        this.prefixResourceName = prefixResourceName;
        this.capabilities = capabilities;
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

    public boolean isAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    public String getSpecificConfig() {
        return specificConfig;
    }

    public void setSpecificConfig(String specificConfig) {
        this.specificConfig = specificConfig;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IaasConfiguration[name=").append(getName())
                .append(", type=").append(getType())
                .append(", subType=").append(getSubType())
                .append(", isDefault=").append(isDefault())
                .append(", autoStart=").append(isAutoStart())
                .append(", specificConfig=").append(getSpecificConfig())
                .append(", prefixResourceName=").append(getPrefixResourceName())
                .append(", capabilities=").append(getCapabilities().toString()).append("]");
        return sb.toString();
    }
}
