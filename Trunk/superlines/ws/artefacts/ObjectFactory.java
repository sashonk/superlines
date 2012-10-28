
package superlines.ws.artefacts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the superlines.ws.artefacts package. 
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

    private final static QName _Authorize_QNAME = new QName("http://ws.server.superlines/", "authorize");
    private final static QName _AuthorizeResponse_QNAME = new QName("http://ws.server.superlines/", "authorizeResponse");
    private final static QName _InvocationContext_QNAME = new QName("http://ws.server.superlines/", "invocationContext");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: superlines.ws.artefacts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthorizeResponse }
     * 
     */
    public AuthorizeResponse createAuthorizeResponse() {
        return new AuthorizeResponse();
    }

    /**
     * Create an instance of {@link Authorize }
     * 
     */
    public Authorize createAuthorize() {
        return new Authorize();
    }

    /**
     * Create an instance of {@link InvocationContext }
     * 
     */
    public InvocationContext createInvocationContext() {
        return new InvocationContext();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authorize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.superlines/", name = "authorize")
    public JAXBElement<Authorize> createAuthorize(Authorize value) {
        return new JAXBElement<Authorize>(_Authorize_QNAME, Authorize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorizeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.superlines/", name = "authorizeResponse")
    public JAXBElement<AuthorizeResponse> createAuthorizeResponse(AuthorizeResponse value) {
        return new JAXBElement<AuthorizeResponse>(_AuthorizeResponse_QNAME, AuthorizeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvocationContext }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.superlines/", name = "invocationContext")
    public JAXBElement<InvocationContext> createInvocationContext(InvocationContext value) {
        return new JAXBElement<InvocationContext>(_InvocationContext_QNAME, InvocationContext.class, null, value);
    }

}
