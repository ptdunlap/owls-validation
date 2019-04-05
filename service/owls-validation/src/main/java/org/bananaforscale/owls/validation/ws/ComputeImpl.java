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

import org.bananaforscale.samples.Operations;
import javax.jws.WebService;
import org.bananaforscale.soap.Compute;

/**
 * A WS based service exposing arithmetic operations
 *
 * @author ptdunlap
 */
@WebService(targetNamespace = "http://soap.bananaforscale.org",
        serviceName = "ComputeService",
        portName = "ComputePort",
        endpointInterface = "org.bananaforscale.soap.Compute")
public class ComputeImpl implements Compute {

    private final Operations op = new Operations();

    /**
     * Adds parameter x to parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the sum of x and y
     */
    @Override
    public Double add(Double x, Double y) {
        return op.add(x, y);
    }

    /**
     * Subtracts parameter y from parameter x
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the result of x minus y
     */
    @Override
    public Double subtract(Double x, Double y) {
        return op.subtract(x, y);
    }

    /**
     * Multiplies parameter x by parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the product of x and y
     */
    @Override
    public Double multiply(Double x, Double y) {
        return op.multiply(x, y);
    }

    /**
     * Divides parameter x by parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the value of x divided by y
     */
    @Override
    public Double divide(Double x, Double y) {
        return op.divide(x, y);
    }

}
