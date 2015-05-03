
package webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DeviceWebService", targetNamespace = "http://WebServices/", wsdlLocation = "http://localhost:8080/IS_Trab2/DeviceWebService?wsdl")
public class DeviceWebService_Service
    extends Service
{

    private final static URL DEVICEWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException DEVICEWEBSERVICE_EXCEPTION;
    private final static QName DEVICEWEBSERVICE_QNAME = new QName("http://WebServices/", "DeviceWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/IS_Trab2/DeviceWebService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DEVICEWEBSERVICE_WSDL_LOCATION = url;
        DEVICEWEBSERVICE_EXCEPTION = e;
    }

    public DeviceWebService_Service() {
        super(__getWsdlLocation(), DEVICEWEBSERVICE_QNAME);
    }

    public DeviceWebService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), DEVICEWEBSERVICE_QNAME, features);
    }

    public DeviceWebService_Service(URL wsdlLocation) {
        super(wsdlLocation, DEVICEWEBSERVICE_QNAME);
    }

    public DeviceWebService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DEVICEWEBSERVICE_QNAME, features);
    }

    public DeviceWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DeviceWebService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DeviceWebService
     */
    @WebEndpoint(name = "DeviceWebServicePort")
    public DeviceWebService getDeviceWebServicePort() {
        return super.getPort(new QName("http://WebServices/", "DeviceWebServicePort"), DeviceWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DeviceWebService
     */
    @WebEndpoint(name = "DeviceWebServicePort")
    public DeviceWebService getDeviceWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://WebServices/", "DeviceWebServicePort"), DeviceWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DEVICEWEBSERVICE_EXCEPTION!= null) {
            throw DEVICEWEBSERVICE_EXCEPTION;
        }
        return DEVICEWEBSERVICE_WSDL_LOCATION;
    }

}
