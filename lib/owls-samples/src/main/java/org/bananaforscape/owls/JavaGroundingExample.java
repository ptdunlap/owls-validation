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
package org.bananaforscape.owls;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.bananaforscale.owls.util.ExecutionUtil;
import org.bananaforscale.samples.Person;
import org.mindswap.exceptions.ExecutionException;

/**
 * Various examples of using the OWL-S Java grounding
 *
 * @author ptdunlap
 */
public class JavaGroundingExample {

    /**
     * An example of executing various services contained within a single
     * ontology making use of the Java Grounding. The inputs and outputs of each
     * service are primitives
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws URISyntaxException
     */
    public void primitiveJavaExample() throws IOException, ExecutionException, URISyntaxException {
        // InputStream to file makes this compatible with shaded artifact
        InputStream in = getClass().getResourceAsStream("/services/primitive_java_grounding_example.owls");
        File temp = File.createTempFile("primitive_java_grounding_example", ".owls");
        FileUtils.copyInputStreamToFile(in, temp);
        URI ontologyURI = temp.toURI();
        URI addURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_java_grounding_example.owls#AddService");
        URI subtractURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_java_grounding_example.owls#SubtractService");
        URI multiplyURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_java_grounding_example.owls#MultiplyService");
        URI divideURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_java_grounding_example.owls#DivideService");
        List<URI> uriList = Arrays.asList(new URI[]{addURI, subtractURI, multiplyURI, divideURI});
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("x", 5.0);
        inputMap.put("y", 3.0);
        List<String> outputKeys = Arrays.asList(new String[]{"z"});
        for (URI serviceURI : uriList) {
            Map<String, Object> resultMap = ExecutionUtil.executeService(ontologyURI, serviceURI, inputMap, outputKeys);
            for (String key : resultMap.keySet()) {
                System.out.println(resultMap.get(key));
            }
        }
        temp.deleteOnExit();
    }

    /**
     * An example of executing a service that uses the Java Grounding along with
     * complex objects (actual Java classes).
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws URISyntaxException
     */
    public void complexJavaExample() throws IOException, ExecutionException, URISyntaxException {
        // InputStream to file makes this compatible with shaded artifact
        InputStream in = getClass().getResourceAsStream("/services/complex_java_grounding_example.owls");
        File temp = File.createTempFile("complex_java_grounding_example", ".owls");
        FileUtils.copyInputStreamToFile(in, temp);
        URI ontologyURI = temp.toURI();
        URI serviceURI = URI.create("http://bananaforscale.org/owls/services/1.2/complex_java_grounding_example.owls#EmployeeService");
        Person person = new Person();
        person.setName("John Smith");
        person.setAge("30");
        person.setHeight("6'0");
        person.setAddress("123 Moon St");
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("Person", person);
        List<String> outputKeys = Arrays.asList(new String[]{"Employee"});
        Map<String, Object> resultMap = ExecutionUtil.executeService(ontologyURI, serviceURI, inputMap, outputKeys);
        for (String key : resultMap.keySet()) {
            System.out.println(resultMap.get(key));
        }
        temp.deleteOnExit();
    }

    public static void main(final String[] args) throws Exception {
        final JavaGroundingExample test = new JavaGroundingExample();
        test.primitiveJavaExample();
        test.complexJavaExample();
    }

}
