
package webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
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

    private final static QName _InsertStateResponse_QNAME = new QName("http://WebServices/", "insertStateResponse");
    private final static QName _InsertState_QNAME = new QName("http://WebServices/", "insertState");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertStateResponse }
     * 
     */
    public InsertStateResponse createInsertStateResponse() {
        return new InsertStateResponse();
    }

    /**
     * Create an instance of {@link InsertState }
     * 
     */
    public InsertState createInsertState() {
        return new InsertState();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "insertStateResponse")
    public JAXBElement<InsertStateResponse> createInsertStateResponse(InsertStateResponse value) {
        return new JAXBElement<InsertStateResponse>(_InsertStateResponse_QNAME, InsertStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "insertState")
    public JAXBElement<InsertState> createInsertState(InsertState value) {
        return new JAXBElement<InsertState>(_InsertState_QNAME, InsertState.class, null, value);
    }

}
