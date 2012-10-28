
package superlines.ws.artefacts;

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
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SuperlinesWebserviceImplService", targetNamespace = "http://ws.server.superlines/", wsdlLocation = "http://localhost:8080/Superlines/service?wsdl")
public class SuperlinesWebserviceImplService
    extends Service
{

    private final static URL SUPERLINESWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException SUPERLINESWEBSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName SUPERLINESWEBSERVICEIMPLSERVICE_QNAME = new QName("http://ws.server.superlines/", "SuperlinesWebserviceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/Superlines/service?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SUPERLINESWEBSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        SUPERLINESWEBSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public SuperlinesWebserviceImplService() {
        super(__getWsdlLocation(), SUPERLINESWEBSERVICEIMPLSERVICE_QNAME);
    }

    public SuperlinesWebserviceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SUPERLINESWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public SuperlinesWebserviceImplService(URL wsdlLocation) {
        super(wsdlLocation, SUPERLINESWEBSERVICEIMPLSERVICE_QNAME);
    }

    public SuperlinesWebserviceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SUPERLINESWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public SuperlinesWebserviceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SuperlinesWebserviceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SuperlinesWebservice
     */
    @WebEndpoint(name = "SuperlinesWebserviceImplPort")
    public SuperlinesWebservice getSuperlinesWebserviceImplPort() {
        return super.getPort(new QName("http://ws.server.superlines/", "SuperlinesWebserviceImplPort"), SuperlinesWebservice.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SuperlinesWebservice
     */
    @WebEndpoint(name = "SuperlinesWebserviceImplPort")
    public SuperlinesWebservice getSuperlinesWebserviceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.server.superlines/", "SuperlinesWebserviceImplPort"), SuperlinesWebservice.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SUPERLINESWEBSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw SUPERLINESWEBSERVICEIMPLSERVICE_EXCEPTION;
        }
        return SUPERLINESWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
