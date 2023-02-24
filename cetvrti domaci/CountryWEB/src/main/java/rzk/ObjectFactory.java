
package rzk;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rzk package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
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

    private final static QName _GetCode_QNAME = new QName("http://webService.rzk/", "getCode");
    private final static QName _GetCodeResponse_QNAME = new QName("http://webService.rzk/", "getCodeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rzk
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCode }
     * 
     */
    public GetCode createGetCode() {
        return new GetCode();
    }

    /**
     * Create an instance of {@link GetCodeResponse }
     * 
     */
    public GetCodeResponse createGetCodeResponse() {
        return new GetCodeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCode }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCode }{@code >}
     */
    @XmlElementDecl(namespace = "http://webService.rzk/", name = "getCode")
    public JAXBElement<GetCode> createGetCode(GetCode value) {
        return new JAXBElement<GetCode>(_GetCode_QNAME, GetCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCodeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webService.rzk/", name = "getCodeResponse")
    public JAXBElement<GetCodeResponse> createGetCodeResponse(GetCodeResponse value) {
        return new JAXBElement<GetCodeResponse>(_GetCodeResponse_QNAME, GetCodeResponse.class, null, value);
    }

}
