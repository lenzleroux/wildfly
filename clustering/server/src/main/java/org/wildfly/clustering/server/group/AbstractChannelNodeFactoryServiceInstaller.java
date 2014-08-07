/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.wildfly.clustering.server.group;

import java.util.Collection;
import java.util.Collections;

import org.jboss.modules.ModuleIdentifier;
import org.jboss.msc.service.ServiceBuilder;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;
import org.wildfly.clustering.spi.ServiceInstaller;
import org.wildfly.clustering.spi.ChannelServiceNames;

/**
 * @author Paul Ferraro
 */
public abstract class AbstractChannelNodeFactoryServiceInstaller implements ServiceInstaller {

    @Override
    public Collection<ServiceName> getServiceNames(String group) {
        return Collections.singleton(ChannelServiceNames.NODE_FACTORY.getServiceName(group));
    }

    @Override
    public Collection<ServiceController<?>> install(ServiceTarget target, String group, ModuleIdentifier moduleId) {
        ServiceName name = ChannelServiceNames.NODE_FACTORY.getServiceName(group);
        ServiceBuilder<ChannelNodeFactory> builder = this.build(target, name, group);
        ServiceController<ChannelNodeFactory> controller = builder.setInitialMode(ServiceController.Mode.ON_DEMAND).install();

        return Collections.<ServiceController<?>>singleton(controller);
    }

    protected abstract ServiceBuilder<ChannelNodeFactory> build(ServiceTarget target, ServiceName name, String group);
}
