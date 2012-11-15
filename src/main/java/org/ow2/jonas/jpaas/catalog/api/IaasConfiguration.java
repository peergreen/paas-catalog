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

package org.ow2.jonas.jpaas.catalog.api;

import java.util.List;
import java.util.Map;

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
    private Map<String,String> capabilities;


    public IaasConfiguration(String name, String type, String subType, boolean isDefault, boolean autoStart,
            String specificConfig, String prefixResourceName, Map<String,String> capabilities) {
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

    public Map<String,String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Map<String,String> capabilities) {
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
