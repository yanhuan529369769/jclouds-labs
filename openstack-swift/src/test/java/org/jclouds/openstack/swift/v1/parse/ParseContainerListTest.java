/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.openstack.swift.v1.parse;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.jclouds.json.BaseSetParserTest;
import org.jclouds.openstack.swift.v1.domain.ContainerMetadata;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;

/**
 * 
 * @author Adrian Cole
 */
@Test(groups = "unit", testName = "ParseContainerListTest")
public class ParseContainerListTest extends BaseSetParserTest<ContainerMetadata> {

   @Override
   public String resource() {
      return "/container_list.json";
   }

   @Override
   @Consumes(MediaType.APPLICATION_JSON)
   public Set<ContainerMetadata> expected() {
      return ImmutableSet
            .of(ContainerMetadata.builder()
                  .name("test_container_1")
                  .count(2)
                  .bytes(78)
                  .build(),
                ContainerMetadata.builder()
                  .name("test_container_2")
                  .count(1)
                  .bytes(17)
                  .build());
   }
}
