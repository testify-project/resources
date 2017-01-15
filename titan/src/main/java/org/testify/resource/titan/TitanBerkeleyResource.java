/*
 * Copyright 2016-2017 Sharmarke Aden.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testify.resource.titan;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import static com.thinkaurelius.titan.diskstorage.configuration.BasicConfiguration.Restriction.NONE;
import com.thinkaurelius.titan.diskstorage.configuration.ModifiableConfiguration;
import com.thinkaurelius.titan.diskstorage.configuration.backend.CommonsConfiguration;
import static com.thinkaurelius.titan.graphdb.configuration.GraphDatabaseConfiguration.ROOT_NS;
import org.testify.ResourceInstance;
import org.testify.ResourceProvider;
import org.testify.TestContext;
import org.testify.core.impl.ResourceInstanceBuilder;
import org.testify.core.util.FileSystemUtil;

/**
 * An implementation of ResourceProvider that provides a Berkeley DB backed
 * Titan graph.
 *
 * @author saden
 */
public class TitanBerkeleyResource implements ResourceProvider<CommonsConfiguration, TitanGraph, Void> {

    private final FileSystemUtil fileSystemUtil = FileSystemUtil.INSTANCE;

    private TitanGraph graph;

    @Override
    public CommonsConfiguration configure(TestContext testContext) {
        String testName = testContext.getName();
        String storageDirectory = fileSystemUtil.createPath("target", "elasticsearch", testName);

        CommonsConfiguration configuration = new CommonsConfiguration();
        configuration.set("storage.backend", "berkeleyje");
        configuration.set("storage.directory", storageDirectory);

        return configuration;
    }

    @Override
    public ResourceInstance<TitanGraph, Void> start(TestContext testContext, CommonsConfiguration config) {
        String storageDirectory = config.get("storage.directory", String.class);
        fileSystemUtil.recreateDirectory(storageDirectory);

        ModifiableConfiguration configuration = new ModifiableConfiguration(ROOT_NS, config, NONE);
        graph = TitanFactory.open(configuration);

        return new ResourceInstanceBuilder<TitanGraph, Void>()
                .server(graph)
                .build();
    }

    @Override
    public void stop() {
        graph.close();
    }

}
