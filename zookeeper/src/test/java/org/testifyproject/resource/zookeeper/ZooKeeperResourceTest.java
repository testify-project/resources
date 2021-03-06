/*
 * Copyright 2016-2017 Testify Project.
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
package org.testifyproject.resource.zookeeper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.test.TestingServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.testifyproject.LocalResourceInstance;
import org.testifyproject.TestContext;
import org.testifyproject.annotation.LocalResource;
import org.testifyproject.annotation.Sut;
import org.testifyproject.junit4.UnitTest;
import org.testifyproject.trait.PropertiesReader;

/**
 *
 * @author saden
 */
@RunWith(UnitTest.class)
public class ZooKeeperResourceTest {

    @Sut
    ZooKeeperResource sut;

    @Test
    public void callToStartResourceShouldReturnRequiredResource() throws Exception {
        TestContext testContext = mock(TestContext.class);
        LocalResource localResource = mock(LocalResource.class, Answers.RETURNS_MOCKS);
        PropertiesReader configReader = mock(PropertiesReader.class);

        given(testContext.getName()).willReturn("test");

        Void config = sut.configure(testContext, localResource, configReader);
        assertThat(config).isNull();

        LocalResourceInstance<TestingServer, CuratorFramework> result = sut.start(
                testContext, localResource, null);

        assertThat(result).isNotNull();
        assertThat(result.getClient()).isPresent();
        assertThat(result.getResource()).isNotNull();

        CuratorFramework client = result.getClient().get().getValue();
        String testPath = client.create().forPath("/test", "test".getBytes());
        assertThat(testPath).isNotNull();

        sut.stop(testContext, localResource, result);
    }

}
