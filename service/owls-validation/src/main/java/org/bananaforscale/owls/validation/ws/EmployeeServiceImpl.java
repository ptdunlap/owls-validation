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
package org.bananaforscale.owls.validation.ws;

import javax.jws.WebService;
import org.bananaforscale.bindings.EmployeeType;
import org.bananaforscale.bindings.PersonType;
import org.bananaforscale.soap.EmployeePortType;

/**
 * A WS service exposing employee functionality
 *
 * @author ptdunlap
 */
@WebService(targetNamespace = "http://soap.bananaforscale.org",
        serviceName = "EmployeeService",
        portName = "EmployeePort",
        endpointInterface = "org.bananaforscale.soap.EmployeePortType")
public class EmployeeServiceImpl implements EmployeePortType {

    /**
     * Enrolls a person as an employee
     *
     * @param person
     * @return
     */
    @Override
    public EmployeeType enroll(PersonType person) {
        EmployeeType emp = new EmployeeType();
        emp.setName(person.getName());
        emp.setAddress(person.getAddress());
        emp.setId(1);
        emp.setPosition("Engineer");
        return emp;
    }

}
