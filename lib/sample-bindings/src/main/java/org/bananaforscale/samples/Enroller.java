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
package org.bananaforscale.samples;

/**
 * Represents functionality a company might have to onboard new employees
 *
 * @author ptdunlap
 */
public class Enroller {

    /**
     * Enrolls a person as an employee
     *
     * @param person
     * @return
     */
    public Employee enroll(Person person) {
        Employee emp = new Employee();
        emp.setName(person.getName());
        emp.setAddress(person.getAddress());
        emp.setId(1);
        emp.setPosition("Engineer");
        return emp;
    }

}
