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
 * Interface for the PaasCatalog facade.
 * @author David Richard
 */
public interface IPaasCatalogFacade {

    /**
     * Get all the PaasConfigurations
     *
     * @return a list of PaasConfiguration
     */
    public List<PaasConfiguration> getPaasConfigurationList();

    /**
     * Get PaasConfigurations of a specific type
     *
     * @param type the type of the PaasConfiguration to retrieve
     * @return a list of PaasConfiguration
     */
    public List<PaasConfiguration> getPaasConfigurationList(String type) throws PaasCatalogException;

    /**
     * Get a specific PaasConfiguration by its name
     *
     * @param name the name of the PaasConfiguration
     * @return the PaasConfiguration
     */
    public PaasConfiguration getPaasConfiguration(String name) throws PaasCatalogException;

    /**
     * Get the default PaasConfiguration name
     *
     * @return the name
     */
    public String getDefaultPaasConfigurationName() throws PaasCatalogException;

}
