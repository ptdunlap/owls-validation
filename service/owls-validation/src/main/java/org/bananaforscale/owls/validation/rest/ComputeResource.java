/*
 * Copyright 2019 ptdunlap.
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
package org.bananaforscale.owls.validation.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bananaforscale.samples.Operations;

/**
 * Resource exposing basic arithmetic operations
 *
 * @author ptdunlap
 */
@Path("compute")
public class ComputeResource {

    private final Operations operations = new Operations();

    /**
     * Adds parameter x to parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the sum of x and y
     */
    @GET
    @Path("add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAdd(@QueryParam("x") Double x, @QueryParam("y") Double y) {
        return Response.ok(operations.add(x, y)).build();
    }

    /**
     * Subtracts parameter y from parameter x
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the result of x minus y
     */
    @GET
    @Path("subtract")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSubtract(@QueryParam("x") Double x, @QueryParam("y") Double y) {
        return Response.ok(operations.subtract(x, y)).build();
    }

    /**
     * Multiplies parameter x by parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the product of x and y
     */
    @GET
    @Path("multiply")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMultiply(@QueryParam("x") Double x, @QueryParam("y") Double y) {
        return Response.ok(operations.multiply(x, y)).build();
    }

    /**
     * Divides parameter x by parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the value of x divided by y
     */
    @GET
    @Path("divide")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDivide(@QueryParam("x") Double x, @QueryParam("y") Double y) {
        return Response.ok(operations.divide(x, y)).build();
    }
}
