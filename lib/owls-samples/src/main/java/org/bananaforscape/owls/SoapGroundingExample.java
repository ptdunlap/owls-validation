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
import org.bananaforscale.bindings.EmployeeType;
import org.bananaforscale.bindings.PersonType;
import org.bananaforscale.owls.util.ExecutionUtil;
import org.bananaforscale.soap.AddResponse;
import org.bananaforscale.soap.DivideResponse;
import org.bananaforscale.soap.MultiplyResponse;
import org.bananaforscale.soap.SubtractResponse;
import org.mindswap.exceptions.ExecutionException;

/**
 * Various examples of using the OWL-S WSDL grounding
 *
 * @author ptdunlap
 */
public class SoapGroundingExample {

    /**
     * An example of executing various services contained within a single
     * ontology making use of the Java Grounding. The inputs and outputs of each
     * service are primitives
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws URISyntaxException
     */
    public void primitiveSoapExample() throws IOException, ExecutionException, URISyntaxException {
        // InputStream to file makes this compatible with shaded artifact
        InputStream in = getClass().getResourceAsStream("/services/primitive_soap_grounding_example.owls");
        File temp = File.createTempFile("primitive_soap_grounding_example", ".owls");
        FileUtils.copyInputStreamToFile(in, temp);
        URI ontologyURI = temp.toURI();
        URI addURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_soap_grounding_example.owls#AddService");
        URI subtractURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_soap_grounding_example.owls#SubtractService");
        URI multiplyURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_soap_grounding_example.owls#MultiplyService");
        URI divideURI = URI.create("http://bananaforscale.org/owls/services/1.2/primitive_soap_grounding_example.owls#DivideService");
        List<URI> uriList = Arrays.asList(new URI[]{addURI, subtractURI, multiplyURI, divideURI});
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("x", 5.0);
        inputMap.put("y", 3.0);

        Map<String, Object> resultMap;
        String key;
        for (URI serviceURI : uriList) {
            switch (serviceURI.getFragment()) {
                case "AddService":
                    key = "zAdd";
                    resultMap = ExecutionUtil.executeService(ontologyURI,
                            serviceURI, inputMap, Arrays.asList(new String[]{key}));
                    if (resultMap.get(key) instanceof AddResponse) {
                        AddResponse add = (AddResponse) resultMap.get(key);
                        System.out.println("add: " + add.getReturn());
                    }
                    break;
                case "SubtractService":
                    key = "zSub";
                    resultMap = ExecutionUtil.executeService(ontologyURI,
                            serviceURI, inputMap, Arrays.asList(new String[]{key}));
                    if (resultMap.get(key) instanceof SubtractResponse) {
                        SubtractResponse sub = (SubtractResponse) resultMap.get(key);
                        System.out.println("sub: " + sub.getReturn());
                    }
                    break;
                case "MultiplyService":
                    key = "zMul";
                    resultMap = ExecutionUtil.executeService(ontologyURI,
                            serviceURI, inputMap, Arrays.asList(new String[]{key}));
                    if (resultMap.get(key) instanceof MultiplyResponse) {
                        MultiplyResponse mul = (MultiplyResponse) resultMap.get(key);
                        System.out.println("mul: " + mul.getReturn());
                    }
                    break;
                case "DivideService":
                    key = "zDiv";
                    resultMap = ExecutionUtil.executeService(ontologyURI,
                            serviceURI, inputMap, Arrays.asList(new String[]{key}));
                    if (resultMap.get(key) instanceof DivideResponse) {
                        DivideResponse div = (DivideResponse) resultMap.get(key);
                        System.out.println("div: " + div.getReturn());
                    }
                    break;
                default:
                    break;
            }
        }
        temp.deleteOnExit();
    }

    /**
     * An example of executing a service that uses the WSDL Grounding along with
     * complex objects (actual Java classes).
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws URISyntaxException
     */
    public void complexSoapExample() throws IOException, ExecutionException, URISyntaxException {
        // InputStream to file makes this compatible with shaded artifact
        InputStream in = getClass().getResourceAsStream("/services/complex_soap_grounding_example.owls");
        File temp = File.createTempFile("complex_soap_grounding_example", ".owls");
        FileUtils.copyInputStreamToFile(in, temp);
        URI ontologyURI = temp.toURI();
        URI serviceURI = URI.create("http://bananaforscale.org/owls/services/1.2/complex_soap_grounding_example.owls#EmployeeService");

        PersonType person = new PersonType();
        person.setName("John Smith");
        person.setAge("30");
        person.setHeight("6'0");
        person.setAddress("123 Moon St");

        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("Person", person);
        List<String> outputKeys = Arrays.asList(new String[]{"Employee"});
        Map<String, Object> resultMap = ExecutionUtil.executeService(ontologyURI, serviceURI, inputMap, outputKeys);
        for (String key : resultMap.keySet()) {
            switch (serviceURI.getFragment()) {
                case "EmployeeService":
                    if (resultMap.get(key) instanceof EmployeeType) {
                        EmployeeType empType = (EmployeeType) resultMap.get(key);
                        System.out.println(empType.getName());
                    }
                    break;
                default:
                    break;
            }
        }
        temp.deleteOnExit();
    }

    public static void main(String[] args) throws Exception {
        SoapGroundingExample example = new SoapGroundingExample();
        example.primitiveSoapExample();
        example.complexSoapExample();
    }
}
