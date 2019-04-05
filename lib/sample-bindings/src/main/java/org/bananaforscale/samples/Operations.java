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
 * Basic arithmetic operations
 *
 * @author ptdunlap
 */
public class Operations {

    /**
     * Adds parameter x to parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the sum of x and y
     */
    public Double add(Double x, Double y) {
        return x + y;
    }

    /**
     * Subtracts parameter y from parameter x
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the result of x minus y
     */
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    /**
     * Multiplies parameter x by parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the product of x and y
     */
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    /**
     * Divides parameter x by parameter y
     *
     * @param x a numerical value
     * @param y a numerical value
     * @return the value of x divided by y
     */
    public Double divide(Double x, Double y) {
        return x / y;
    }

}
