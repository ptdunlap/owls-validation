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

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bananaforscale.samples.Employee;
import org.bananaforscale.samples.Enroller;
import org.bananaforscale.samples.Person;

/**
 * Resource for handling employees
 *
 * @author ptdunlap
 */
@Path("employee")
public class EmployeeResource {

    private final Enroller enroller = new Enroller();

    @POST
    @Path("/enroll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee enroll(Person person) {
        return enroller.enroll(person);
//        return Response.ok(enroller.enroll(person)).build();
    }

}
