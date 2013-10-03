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

/**
 * Interface for the IaasCatalog facade.
 * @author David Richard
 */
public interface IIaasCatalogFacade {

    /**
     * Get all the IaasConfigurations
     *
     * @return a list of IaasConfiguration
     */
    public List<IaasConfiguration> getIaasConfigurationList();

    /**
     * Get the default IaasConfiguration name
     *
     * @return the name
     * @throws org.ow2.jonas.jpaas.catalog.api.IaasCatalogException
     */
    public String getDefaultIaasConfigurationName() throws IaasCatalogException;

    /**
     * Get a specific IaasConfiguration by its name
     *
     * @param name the name of the IaasConfiguration
     * @return the IaasConfiguration
     * @throws org.ow2.jonas.jpaas.catalog.api.IaasCatalogException
     */
    public IaasConfiguration getIaasConfiguration(String name) throws IaasCatalogException;
}
