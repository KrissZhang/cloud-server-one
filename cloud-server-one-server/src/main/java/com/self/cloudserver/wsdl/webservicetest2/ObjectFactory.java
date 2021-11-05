
package com.self.cloudserver.wsdl.webservicetest2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.self.cloudserver.wsdl.webservicetest2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TestWebService2Response_QNAME = new QName("http://webservice.cloudserver.self.com/", "testWebService2Response");
    private final static QName _TestWebService2_QNAME = new QName("http://webservice.cloudserver.self.com/", "testWebService2");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.self.cloudserver.wsdl.webservicetest2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestWebService2 }
     * 
     */
    public TestWebService2 createTestWebService2() {
        return new TestWebService2();
    }

    /**
     * Create an instance of {@link TestWebService2Response }
     * 
     */
    public TestWebService2Response createTestWebService2Response() {
        return new TestWebService2Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestWebService2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cloudserver.self.com/", name = "testWebService2Response")
    public JAXBElement<TestWebService2Response> createTestWebService2Response(TestWebService2Response value) {
        return new JAXBElement<TestWebService2Response>(_TestWebService2Response_QNAME, TestWebService2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestWebService2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cloudserver.self.com/", name = "testWebService2")
    public JAXBElement<TestWebService2> createTestWebService2(TestWebService2 value) {
        return new JAXBElement<TestWebService2>(_TestWebService2_QNAME, TestWebService2 .class, null, value);
    }

}
