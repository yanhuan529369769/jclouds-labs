/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jclouds.profitbricks.rest.features;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import java.io.Closeable;
import java.util.List;
import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.jclouds.profitbricks.rest.binder.snapshot.UpdateSnapshotRequestBinder;
import org.apache.jclouds.profitbricks.rest.domain.Snapshot;
import org.apache.jclouds.profitbricks.rest.domain.options.DepthOptions;
import org.jclouds.Fallbacks;
import org.jclouds.Fallbacks.EmptyListOnNotFoundOr404;
import org.jclouds.http.filters.BasicAuthentication;
import org.jclouds.http.functions.ParseJson;
import org.jclouds.json.Json;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.MapBinder;
import org.jclouds.rest.annotations.PATCH;
import org.jclouds.rest.annotations.PayloadParam;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.ResponseParser;
import org.jclouds.rest.annotations.SelectJson;

@Path("/snapshots")
@RequestFilters(BasicAuthentication.class)
public interface SnapshotApi extends Closeable {
   
   @Named("snapshot:list")
   @GET
   @SelectJson("items")
   @Fallback(EmptyListOnNotFoundOr404.class)
   List<Snapshot> list();

   @Named("snapshot:list")
   @GET
   @SelectJson("items")
   @Fallback(EmptyListOnNotFoundOr404.class)
   List<Snapshot> list(DepthOptions options);
   
   @Named("snapshot:get")
   @GET   
   @Path("/{snapshotId}")
   @ResponseParser(SnapshotApi.SnapshotParser.class)
   @Fallback(Fallbacks.NullOnNotFoundOr404.class)
   Snapshot get(@PathParam("snapshotId") String snapshotId);

   @Named("snapshot:get")
   @GET   
   @Path("/{snapshotId}")
   @ResponseParser(SnapshotApi.SnapshotParser.class)
   @Fallback(Fallbacks.NullOnNotFoundOr404.class)
   Snapshot get(@PathParam("snapshotId") String snapshotId, DepthOptions options);
   
   @Named("snapshot:update")
   @PATCH
   @Path("/{snapshotId}")
   @MapBinder(UpdateSnapshotRequestBinder.class)
   @ResponseParser(SnapshotApi.SnapshotParser.class)
   @Produces("application/vnd.profitbricks.partial-properties+json")
   Snapshot update(@PayloadParam("snapshot") Snapshot.Request.UpdatePayload payload);
   
   @Named("snapshot:delete")
   @DELETE
   @Path("/{snapshotId}")
   @Fallback(Fallbacks.VoidOnNotFoundOr404.class)
   void delete(@PathParam("snapshotId") String snapshotId);

      
   static final class SnapshotParser extends ParseJson<Snapshot> {
      @Inject SnapshotParser(Json json) {
         super(json, TypeLiteral.get(Snapshot.class));
      }
   }
   
}
