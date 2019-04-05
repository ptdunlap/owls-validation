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
package org.bananaforscape;

import java.io.IOException;
import java.net.URISyntaxException;
import org.bananaforscape.owls.JavaGroundingExample;
import org.bananaforscape.owls.RestGroundingExample;
import org.bananaforscape.owls.SoapGroundingExample;
import org.mindswap.exceptions.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Driver class to run the individual examples from the command line
 *
 * @author ptdunlap
 */
public class ExampleDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleDriver.class);

    private static final String USAGE_GUIDE
            = "Usage Syntax:  java -jar owls-samples-standalone.jar {example name}\n"
            + "Options:       java, soap, rest, all\n"
            + "Example:       java -jar owls-samples-standalone.jar soap";

    private void processArgs(String exampleName) {
        JavaGroundingExample jExample = new JavaGroundingExample();
        SoapGroundingExample sExample = new SoapGroundingExample();
        RestGroundingExample rExample = new RestGroundingExample();
        try {
            switch (exampleName) {
                case "java":
                    jExample.primitiveJavaExample();
                    jExample.complexJavaExample();
                    break;
                case "soap":
                    sExample.primitiveSoapExample();
                    sExample.complexSoapExample();
                    break;
                case "rest":
                    rExample.primitiveRestExample();
                    rExample.complexRestExample();
                    break;
                case "all":
                    jExample.primitiveJavaExample();
                    jExample.complexJavaExample();
                    sExample.primitiveSoapExample();
                    sExample.complexSoapExample();
                    rExample.primitiveRestExample();
                    rExample.complexRestExample();
                    break;
                default:
                    System.out.println("Invalid example name provided");
                    break;
            }
        } catch (IOException | ExecutionException | URISyntaxException ex) {
            LOGGER.error("An error occurred while executing example", ex);
        }

    }

    /**
     * Entry-point for the ontology loader. Reads the property file path off the
     * command-line, reads the property file, unmarshalls the contents, and
     * loads the properties into ZooKeeper.
     *
     * @param args the command-line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        ExampleDriver driver = new ExampleDriver();
        if (args.length == 0) {
            System.out.println(USAGE_GUIDE);
        } else {
            driver.processArgs(args[0]);
        }
    }

}
