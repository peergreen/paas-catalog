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

package org.ow2.jonas.jpaas.catalog.facade.api;

import org.ow2.jonas.jpaas.catalog.facade.object.PaasConfiguration;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for the PaasCatalog facade.
 * @author David Richard
 */
@Remote
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
    public List<PaasConfiguration> getPaasConfigurationList(String type);

    /**
     * Get a specific PaasConfiguration by its name
     *
     * @param name the name of the PaasConfiguration
     * @return the PaasConfiguration
     */
    public PaasConfiguration getPaasConfiguration(String name);

    /**
     * Get the default PaasConfiguration name
     *
     * @return the name
     */
    public String getDefaultPaasConfigurationName();

}
