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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
 * Various examples of using the OWL-S WADL grounding
 *
 * @author ptdunlap
 */
public class RestGroundingExample {

    /**
     * An example of executing various services contained within a single
     * ontology making use of the REST Grounding. The inputs and outputs of each
     * service are primitives
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws URISyntaxException
     */
    public void primitiveRestExample() throws IOException, ExecutionException, URISyntaxException {
        InputStream in = getClass().getResourceAsStream("/services/primitive_rest_grounding_example.owls");
        File temp = File.createTempFile("primitive_rest_grounding_example", ".owls");
        FileUtils.copyInputStreamToFile(in, temp);
        URI ontologyURI = temp.toURI();
        URI addURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_rest_grounding_example.owls#AddService");
        URI subtractURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_rest_grounding_example.owls#SubtractService");
        URI multiplyURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_rest_grounding_example.owls#MultiplyService");
        URI divideURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_rest_grounding_example.owls#DivideService");
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
     * An example of executing a service that uses the WADL Grounding along with
     * complex objects (actual Java classes).
     *
     * http://localhost:8080/owls-validation/owls-validation.wadl
     *
     * {"name":"John Smith","age":"30","height":"6'0","address":"123 Moon St"}
     *
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     * @throws org.mindswap.exceptions.ExecutionException
     */
    public void complexRestExample() throws IOException, ExecutionException, URISyntaxException {
        InputStream in = getClass().getResourceAsStream("/services/complex_rest_grounding_example.owls");
        File temp = File.createTempFile("complex_rest_grounding_example", ".owls");
        FileUtils.copyInputStreamToFile(in, temp);
        URI ontologyURI = temp.toURI();
        URI serviceURI = URI.create("http://bananaforscale.org/owls/services/1.2/complex_rest_grounding_example.owls#EmployeeService");
        Person person = new Person();
        person.setName("John Smith");
        person.setAge("30");
        person.setHeight("6'0");
        person.setAddress("123 Moon St");
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("Person", marshal(person));
        List<String> outputKeys = Arrays.asList(new String[]{"Employee"});
        Map<String, Object> resultMap = ExecutionUtil.executeService(ontologyURI, serviceURI, inputMap, outputKeys);
        for (String key : resultMap.keySet()) {
            System.out.println(resultMap.get(key));
        }
        temp.deleteOnExit();
    }

    public static void main(String[] args) throws Exception {
        RestGroundingExample example = new RestGroundingExample();
        example.primitiveRestExample();
        example.complexRestExample();
    }

    private String marshal(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
